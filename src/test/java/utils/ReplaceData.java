package utils;

import org.apache.commons.collections.CollectionUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ReplaceData {

    private static final String sqlRepair1 = "update crm.crmchance set chanceType = 'Rubbish' where id in  ";
    private static final String sqlRepair2 = ";";
    private static final String newLine = System.getProperty("line.separator");
    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        List<Long> chanceIds = new ArrayList<>();
        File file = new File("src/chanceIds.txt");
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String tempString;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                if ("".equals(tempString)) {
                    continue;
                }
                chanceIds.add(Long.valueOf(tempString));
            }
        }

        int page = 0;
        int size = 1000;
        List<Long> tempChanceIds;
        List<String> sqls = new ArrayList<>();
        do {
            tempChanceIds = chanceIds.stream().skip(page * size).limit(size).collect(Collectors.toList());
            if (CollectionUtils.isNotEmpty(tempChanceIds)) {
                String sql = tempChanceIds.stream().map(String::valueOf).collect(Collectors.joining(",","(",")"));
                sqls.add(sqlRepair1 + sql + sqlRepair2);
            }
            page ++ ;
        }
        while (tempChanceIds.size() == size);

        try (FileOutputStream fileOutputStream = new FileOutputStream("src/repairSqls2.txt")) {
            sqls.forEach(sql -> {
                try {
                    fileOutputStream.write(sql.getBytes());
                    fileOutputStream.write(newLine.getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            fileOutputStream.flush();
        }
    }
}
