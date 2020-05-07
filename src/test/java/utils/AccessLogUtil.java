package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.time.DateFormatUtils;

import com.alibaba.fastjson.JSONObject;

import utils.entity.AggsEntity;
import utils.entity.EsHit;
import utils.entity.EsHit2;
import utils.entity.LabelInfo;

public class AccessLogUtil {
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
        File file = new File("src/chances123.txt");
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String tempString;
            while ((tempString = reader.readLine()) != null) {
                if ("".equals(tempString)) {
                    continue;
                }
                sb.append(tempString);
            }
        }
        List<JSONObject> esHits = JSONObject.parseArray(sb.toString(),JSONObject.class);

        esHits.forEach(a -> {
            System.out.println(a.get("userId") + "%" + a.get("username") + "%" + a.get("personName") + "%" + a.get("groupName") + "%" + a.get("categoryName") + "%" + a.get("parentCategoryName") + "%" + a.get("cateType") + "%" + a.get("accessCount"));
        });


    }
}
