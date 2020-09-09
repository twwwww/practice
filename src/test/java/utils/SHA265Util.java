package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.time.DateFormatUtils;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;

import utils.entity.AggsEntity;
import utils.entity.EsHit;
import utils.entity.EsHit2;
import utils.entity.LabelInfo;

public class SHA265Util {


    private static Map<Integer,String> orderTypeMapper = Maps.newHashMap();
    private static Map<Long,String> categoryMapper = Maps.newHashMap();
    /**
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        parseJsonStr();
    }

    private static void parseJsonStr() throws Exception {
        List<String[]> lines = Lists.newArrayList();
        File file = new File("src/text.txt");
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String tempString;
            while ((tempString = reader.readLine()) != null) {
                if ("".equals(tempString)) {
                    continue;
                }
                lines.add(new String[]{tempString});
            }
        }

        lines.forEach(arr -> {
            String s = sha256(arr[0]);
            System.out.println(s);
        });

//        List<Long> chanceIds = esHits.stream().map(ch -> ch.get_source().getId()).collect(Collectors.toList());
//        System.out.println(chanceIds);
//        chanceIds.forEach(System.out::println);
    }

    /**
     * 字符串 SHA 加密**
     * @param strText
     * @return
     */
    public static String sha256(final String strText) {
        // 是否是有效字符串
        if (strText != null && strText.length() > 0) {
            try {
                // SHA 加密开始
                // 创建加密对象 并傳入加密類型
                MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
                // 传入要加密的字符串
                messageDigest.update(strText.getBytes());
                // 得到 byte 類型结果
                return byteToHex(messageDigest.digest());
            } catch (NoSuchAlgorithmException e) {
                return "";
            }
        } else {
            return "";
        }

    }

    /**
     * 将byte转为16进制
     * @param bytes
     * @return
     */
    private static String byteToHex(byte[] bytes) {
        if (bytes == null) {
            return null;
        }
        StringBuffer sb = new StringBuffer();
        String temp = null;
        for (int i = 0; i < bytes.length; i++) {
            temp = Integer.toHexString(bytes[i] & 0xFF);
            if (temp.length() == 1) {
                // 1得到一位的进行补0操作
                sb.append(0);
            }
            sb.append(temp);
        }
        return sb.toString();
    }
}
