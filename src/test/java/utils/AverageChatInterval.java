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
                    "                            \"gt\": 1581616800000,\n" +
                    "                            \"lt\": 1581629400000\n" +
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

            Response response = Dsl.asyncHttpClient()
                    .prepareGet(url)
                    .addHeader("contentType", "application/json")
                    .addHeader("Authorization", "Basic " + buildAuthHeaderInfo())
                    .setBody(query)
                    .execute()
                    .get();
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
            for (int i = 0; i < list.size(); i++) {
                HujingChatRecordHit hit = list.get(i);
                HujingChatRecord chat = hit.get_source();
                String chatStr = chat.getUser_wechat() + "%==%" + chat.getWechat();
                if (chat.getSend_type() == 2) {
                    mapper.putIfAbsent(chatStr, chat.getChat_time().getTime());
                } else {
                    Long time = mapper.get(chatStr);
                    if (Objects.nonNull(time)) {
                        mapper.remove(chatStr);
                        long interval = (chat.getChat_time().getTime() - time) / 1000;
                        if (interval < 0) {
                            System.out.println("---==" + chatStr);
                            System.out.println("---" + chat.getId());
                        }
                        if (interval <= 12.5 * 60 * 60) {
                            talkCount++;
                            chatIntervalTime += interval;
                        }
                    }
                }
            }
            loopCount++;
            System.out.println("loopCount = " + loopCount);
            System.out.println("gtTime = " + gtTime);
            System.out.println("talkCount = " + talkCount);
            System.out.println("chatIntervalTime = " + chatIntervalTime);
            System.out.println("averageChatInterval = " + chatIntervalTime / talkCount);
        } while (list.size() > 0);
        System.out.println("COMPLETE =========");
        System.out.println("talkCount = " + talkCount);
        System.out.println("chatIntervalTime = " + chatIntervalTime);
        System.out.println("averageChatInterval = " + chatIntervalTime / talkCount);
        return result;
    }

    private static String buildAuthHeaderInfo() {
        String plainCreds = "crm:QydpnhkWpqw";
        byte[] plainCredsBytes = plainCreds.getBytes();
        byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
        String base64Creds = new String(base64CredsBytes);
        return base64Creds;
    }

}
