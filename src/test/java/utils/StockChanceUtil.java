package utils;

import com.alibaba.fastjson.JSONObject;
import utils.entity.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;
import java.util.stream.Collectors;

public class StockChanceUtil {
    /**
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        List<EsHit> esHits = parseJsonStr();
        List<String> chanceIds = esHits.stream().map(EsHit::get_id).collect(Collectors.toList());
        System.out.println(chanceIds);
    }

    private static List<EsHit> parseJsonStr() throws Exception {
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
        return esHits;
    }
}