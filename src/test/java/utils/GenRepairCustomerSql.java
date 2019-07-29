package utils;

import org.apache.commons.collections.CollectionUtils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GenRepairCustomerSql {
    private static final String driver = "com.mysql.jdbc.Driver";
    private static final String url3306 = "jdbc:mysql://117.50.62.185:3306/highso";
    private static final String url3308 = "jdbc:mysql://117.50.62.185:3308/crm";
    private static final String username = "prod-crm-read";
    private static final String password = "b5L9vfGYPbiL";
    private static final String sqlDup = "select cs.id,cs.customerId,cs.mobile\n" + "from crmcustomer cs\n"
            + "inner join (select cs_in.customerId\n" + "from crmcustomer cs_in\n"
            + "WHERE cs_in.customerId is not null \n"
            + "GROUP BY cs_in.customerId HAVING count(cs_in.id) > 1) as dup on dup.customerId = cs.customerId \n";

    private static final String sqlCus = "select cus.id,cus.mobile from customer cus WHERE mobile in";
    private static final String sqlRepair1 = "update crm.crmcustomer set customerId = ";
    private static final String sqlRepair2 = " WHERE id =";
    private static final String sqlRepair3 = ";";
    private static final String newLine = System.getProperty("line.separator");


    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        Class.forName(driver);

        List<CrmCustomer> crmCustomerList = new ArrayList<>();
        try (Connection connection3308 = DriverManager.getConnection(url3308, username, password)) {
            PreparedStatement statement = connection3308.prepareStatement(sqlDup);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                CrmCustomer crmCustomer = new CrmCustomer();
                crmCustomer.setId(resultSet.getLong(1));
                crmCustomer.setCustomerId(resultSet.getLong(2));
                crmCustomer.setMobile(resultSet.getString(3));
                crmCustomerList.add(crmCustomer);
            }
        }
        String mobileInStr =
                crmCustomerList.stream().map(CrmCustomer::getMobile).collect(Collectors.joining(",", "(", ")"));

        List<Customer> customerList = new ArrayList<>();
        try (Connection connection3306 = DriverManager.getConnection(url3306, username, password)) {
            PreparedStatement statement = connection3306.prepareStatement(sqlCus + mobileInStr);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Customer customer = new Customer();
                customer.setId(resultSet.getLong(1));
                customer.setMobile(resultSet.getString(2));
                customerList.add(customer);
            }
        }
        Map<String, List<Customer>> mobileMapper =
                customerList.stream().collect(Collectors.groupingBy(Customer::getMobile));

        List<String> repairSqls = new ArrayList<>();
        List<Long> toNullCrmcustomerIds = new ArrayList<>();
        crmCustomerList.forEach(crmCustomer -> {
            List<Customer> customers = mobileMapper.get(crmCustomer.getMobile());
            if (CollectionUtils.isNotEmpty(customers)) {
                if (customers.stream().noneMatch(customer -> crmCustomer.getCustomerId().equals(customer.getId()))) {
                    repairSqls.add(repairSql(crmCustomer.getId(), customers.get(0).getId()));
                }
            } else {
                toNullCrmcustomerIds.add(crmCustomer.getId());
            }
        });
        if (CollectionUtils.isNotEmpty(toNullCrmcustomerIds)) {
            String toNullSql = toNullCrmcustomerIds.stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(",", sqlRepair1 + "null where id in (", ");"));
            repairSqls.add(toNullSql);
        }
        System.out.println(repairSqls);
        try (FileOutputStream fileOutputStream = new FileOutputStream("src/repairSqls.txt")) {
            repairSqls.forEach(sql -> {
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

    private static String repairSql(Long crmCustomerId, Long customerId) {
        return sqlRepair1 + customerId + sqlRepair2 + crmCustomerId + sqlRepair3;
    }
}
