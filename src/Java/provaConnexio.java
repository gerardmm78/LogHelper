package Java;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;

public class provaConnexio {

    public static void main(String[]args) throws IOException, SQLException {

        Connection connection = DriverManager.getConnection("jdbc:mariadb://172.16.0.100:3306/loghelperdb", "loghelper", "1234");
        String tableName = "ASA";
        boolean tExists = false;

        try (ResultSet rs = connection.getMetaData().getTables(null, null, tableName, null)){

            while (rs.next()){
                String tName = rs.getString("TABLE_NAME");
                if (tName != null && tName.equals(tableName)){
                    tExists = true;
                    break;
                } else {
                    System.out.println("No tables");
                }
            }

        }

        System.out.println(tExists);

    }

}
