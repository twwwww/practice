package utils;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.collections.CollectionUtils;
import org.asynchttpclient.Dsl;
import org.asynchttpclient.Response;
import utils.entity.HujingChatRecord;
import utils.entity.HujingChatRecordHit;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class AverageChatInterval {

    private static final String url = "http://es-query.h.highso.com.cn:9202/hujing_chat_record_index_202002/_search";

    private static Map<String, Long> mapper = Maps.newHashMap();



    public static void main(String[] args) throws Exception {
        countGroupByLabelAndCategory();
    }


    /**
     * @throws Exception
     */
    @SuppressWarnings("checkstyle:SeparatorWrap")
    private static List countGroupByLabelAndCategory() throws Exception {

        Map<Long,CountTime> averageMapper = Maps.newHashMap();
        Map<String,Long> wechatMapper = Maps.newHashMap();

        Map<String,Long> timeMapper = Maps.newHashMap();

        File file = new File("src/wechatUser.txt");
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String tempString;
            while ((tempString = reader.readLine()) != null) {
                String[] split = tempString.replace(" ", "").replace("\t","").split(",");
                CountTime countTime = new CountTime();
                countTime.name   = split[7] + "\t" + split[5] + "\t" + split[3];
                countTime.id = Long.valueOf(split[2]);
                countTime.count = 0;
                countTime.time = 0;
                averageMapper.put(countTime.id,countTime);
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
                    "                            \"gt\": 1582221600000,\n" +
                    "                            \"lt\": 1582234200000\n" +
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
                HujingChatRecord chat = hit.get_source();
                String chatStr = chat.getUser_wechat() + "%==%" + chat.getWechat();
                if (chat.getSend_type() == 2) {
                    timeMapper.putIfAbsent(chatStr, chat.getChat_time().getTime());
                } else {
                    //Long time = mapper.get(chatStr);
                    Long time = timeMapper.get(chatStr);
                    if (Objects.nonNull(time)) {
                        //mapper.remove(chatStr);
                        timeMapper.remove(chatStr);
                        long interval = (chat.getChat_time().getTime() - time) / 1000;
                        if (interval < 0) {
                            System.out.println("---==" + chatStr);
                            System.out.println("---" + chat.getId());
                        }
                        if (interval <= 12.5 * 60 * 60) {
                            CountTime countTime = averageMapper.get(wechatMapper.get(chat.getUser_wechat()));
                            if (countTime != null) {
                                countTime.count++;
                                countTime.time += interval;
                            }
                            //talkCount++;
                            //chatIntervalTime += interval;
                        }
                    }
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
            if (mapper.time > 0 && mapper.count > 0) {
                System.out.println(mapper.name + " : " + mapper.time / mapper.count);
            }
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
        long time;
        Long id;
        String name;
    }

}
