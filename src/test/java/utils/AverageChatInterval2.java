package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.collections.CollectionUtils;
import org.asynchttpclient.Dsl;
import org.asynchttpclient.Response;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;

import utils.entity.HujingChatRecordHit;

public class AverageChatInterval2 {

    private static final String url = "http://es-query.h.highso.com.cn:9202/hujing_chat_record_index_202003/_search";

    private static Map<String, Long> mapper = Maps.newHashMap();



    public static void main(String[] args) throws Exception {
        countGroupByLabelAndCategory();
    }


    /**
     * @throws Exception
     */
    @SuppressWarnings("checkstyle:SeparatorWrap")
    private static List countGroupByLabelAndCategory() throws Exception {

        Map<String,CountTime> averageMapper = Maps.newHashMap();
        Map<String,Long> wechatMapper = Maps.newHashMap();

        Map<String,Long> timeMapper = Maps.newHashMap();

        File file = new File("src/wechatUser2.txt");
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String tempString;
            while ((tempString = reader.readLine()) != null) {
                String[] split = tempString.replace(" ", "").replace("\t","").split(",");
                CountTime countTime = new CountTime();
                countTime.name   = split[3] + "\t" + split[0] + "\t" + split[1] + "\t" + split[2];
                countTime.count = 0;
                countTime.time = 0;
                averageMapper.put(split[2],countTime);
                wechatMapper.put(split[1],countTime.id);
            }
        }



        List result = new ArrayList<>();
        List<HujingChatRecordHit> list;
        long talkCount = 0;
        long chatIntervalTime = 0;
        int loopCount = 0;

        long gtTime = 0;


        do {
            String query = "{\n" +
                    "    \"query\": {\n" +
                    "        \"bool\": {\n" +
                    "            \"must\": [\n" +
                    "                {\n" +
                    "                    \"range\": {\n" +
                    "                        \"chat_time\": {\n" +
                    "                            \"gt\": 1583053200000,\n" +
                    "                            \"lt\": 1583087400000\n" +
                    "                        }\n" +
                    "                    }\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"range\": {\n" +
                    "                        \"chat_time\": {\n" +
                    "                            \"gt\": " + gtTime + "\n" +
                    "                        }\n" +
                    "                    }\n" +
                    "                }\n" +
                    "            ],\n" +
                    "            \"must_not\": [\n" +
                    "                {\n" +
                    "                    \"term\": {\n" +
                    "                        \"is_group_chat\": 1\n" +
                    "                    }\n" +
                    "                }\n" +
                    "            ],\n" +
                    "            \"should\": []\n" +
                    "        }\n" +
                    "    },\n" +
                    "    \"from\": 0,\n" +
                    "    \"size\": 10000,\n" +
                    "    \"sort\": [\n" +
                    "        {\n" +
                    "            \"chat_time\": \"asc\"\n" +
                    "        }\n" +
                    "    ],\n" +
                    "    \"aggs\": {}\n" +
                    "}";

            Response response = null;

            int matchCount = 0;

            do {
                try {
                    response = Dsl.asyncHttpClient()
                            .prepareGet(url)
                            .addHeader("contentType", "application/json")
                            .addHeader("Authorization", "Basic " + buildAuthHeaderInfo())
                            .setBody(query)
                            .execute()
                            .get();
                }catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("====retry====");
                }

            }
            while (response == null);

            if (response.getStatusCode() != 200) {
                return null;
            }

            String responseBody = response.getResponseBody();
            list = JSONObject.parseArray(
                    JSONObject.parseObject(responseBody).getJSONObject("hits").getJSONArray("hits").toString(),
                    HujingChatRecordHit.class);
            if (CollectionUtils.isEmpty(list)) {
                break;
            }
            gtTime = list.get(list.size() - 1).get_source().getChat_time().getTime() + 8 * 60 * 60 * 1000;
            for (HujingChatRecordHit hit : list) {
                CountTime countTime = averageMapper.get(hit.get_source().getWechat());
                if (Objects.nonNull(countTime)) {
                    matchCount++;
                    if (hit.get_source().getSend_type() == 2) {
                        countTime.count2 ++ ;
                    } else {
                        countTime.count ++ ;
                    }
                } else {
                    System.out.println(hit.get_source().getWechat());
                }
            }
            loopCount++;
            System.out.println("loopCount = " + loopCount);
            System.out.println("gtTime = " + gtTime);
            System.out.println("talkCount = " + talkCount);
            System.out.println("chatIntervalTime = " + chatIntervalTime);
            //System.out.println("averageChatInterval = " + chatIntervalTime / talkCount);
        } while (list.size() > 0);
        System.out.println("COMPLETE =========");
        System.out.println("talkCount = " + talkCount);
        System.out.println("chatIntervalTime = " + chatIntervalTime);
        //System.out.println("averageChatInterval = " + chatIntervalTime / talkCount);

        averageMapper.values().forEach(mapper -> {
                System.out.println(mapper.name + "\t" + mapper.count  + "\t"  +  mapper.count2);

        });

        return result;
    }

    private static String buildAuthHeaderInfo() {
        String plainCreds = "crm:QydpnhkWpqw";
        byte[] plainCredsBytes = plainCreds.getBytes();
        byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
        String base64Creds = new String(base64CredsBytes);
        return base64Creds;
    }

    private static class CountTime {
        long count;
        long count2;
        long time;
        Long id;
        String name;
    }

}
