package utils;

import java.awt.Label;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import utils.entity.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class StockChanceUtil {
    /**
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        parseJsonStr();
    }

    private static void parseJsonStr() throws Exception {
        StringBuilder sb = new StringBuilder();
        File file = new File("src/chances.txt");
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String tempString;
            while ((tempString = reader.readLine()) != null) {
                if ("".equals(tempString)) {
                    continue;
                }
                sb.append(tempString);
            }
        }
        List<EsHit> esHits = JSONObject.parseArray(sb.toString(), EsHit.class);
        List<Long> chanceIds = esHits.stream().map(ch -> ch.get_source().getId()).collect(Collectors.toList());
        System.out.println(chanceIds);
//        chanceIds.forEach(System.out::println);
    }

    private static void parseJsonStr2() throws Exception {
        StringBuilder sb = new StringBuilder();
        File file = new File("src/chances.txt");
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String tempString;
            while ((tempString = reader.readLine()) != null) {
                if ("".equals(tempString)) {
                    continue;
                }
                sb.append(tempString);
            }
        }
        List<EsHit> esHits = JSONObject.parseArray(sb.toString(), EsHit.class);
        List<String> chanceIds = esHits.stream().map(ch -> {
            StringBuilder sbMerge = new StringBuilder();
            sbMerge.append(ch.get_source().getId()).append("=");
            sbMerge.append(ch.get_source().getCustomerId()).append("=");
            sbMerge.append(ch.get_source()
                    .getLabels()
                    .stream()
                    .filter(label -> label.getLabelId() == 283)
                    .findAny()
                    .map(LabelInfo::getCreateTime)
                    .map(time -> DateFormatUtils.format(time, "yyyy-MM-dd HH:mm:ss"))
                    .orElse(null)).append("=");
            sbMerge.append(ch.get_source()
                    .getLabels()
                    .stream()
                    .filter(label -> label.getLabelId() == 283)
                    .findAny()
                    .map(LabelInfo::getUpdateTime)
                    .map(time -> DateFormatUtils.format(time, "yyyy-MM-dd HH:mm:ss"))
                    .orElse(null));
            return sbMerge.toString();
        }).collect(Collectors.toList());
        chanceIds.forEach(System.out::println);
    }

    private static void parseAggsJsonStr() throws Exception {
        StringBuilder sb = new StringBuilder();
        File file = new File("src/chances.txt");
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String tempString;
            while ((tempString = reader.readLine()) != null) {
                if ("".equals(tempString)) {
                    continue;
                }
                sb.append(tempString);
            }
        }
        List<AggsEntity> esHits = JSONObject.parseArray(sb.toString(), AggsEntity.class);
        List<String> chanceIds = esHits.stream().map(AggsEntity::getKey).collect(Collectors.toList());
        System.out.println(chanceIds);
    }

    private static void parse12wechatJsonStr() throws Exception {
        StringBuilder sb = new StringBuilder();
        File file = new File("src/chances.txt");
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String tempString;
            while ((tempString = reader.readLine()) != null) {
                if ("".equals(tempString)) {
                    continue;
                }
                sb.append(tempString);
            }
        }
        List<EsHit2> esHits = JSONObject.parseArray(sb.toString(), EsHit2.class);
        List<String> wechates = esHits.stream().map(ch -> ch.get_source().getWechat()).distinct().collect(Collectors.toList());
        wechates.forEach(wechat -> System.out.println("'" + wechat + "',"));
//        chanceIds.forEach(System.out::println);
    }
}
