package Java;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;

public class provaConnexio {

    public static void main(String[]args) throws IOException, SQLException {

        Connection connection = DriverManager.getConnection("jdbc:mariadb://172.16.0.100:3306/loghelperdb", "loghelper", "1234");

    }

}
