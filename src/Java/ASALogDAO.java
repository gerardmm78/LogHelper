package Java;

import java.sql.*;
import java.util.List;

public class ASALogDAO implements LogDAO {

    @Override
    public void createTable() throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:mariadb://172.16.0.100:3306/loghelperdb", "loghelper", "1234");

        String ASATable = "asa";
        String ErrorTable = "error";

        //Mirar si hi ha una taula on emmagatzemar la llista Asa log, per sinó crear-la
        if (!tableExists(connection, ASATable)){

            String newTable =
                    "CREATE TABLE " + ASATable + " ("
                    + "idLog INT(64) NOT NULL AUTO_INCREMENT,"
                    + "name VARCHAR(100) NOT NULL,"
                    + "start DATETIME NOT NULL,"
                    + "msg VARCHAR(200) NOT NULL,"
                    + "suser VARCHAR(100) NOT NULL,"
                    + "src VARCHAR(200) NOT NULL,"
                    + "spt INT(100) NOT NULL,"
                    + "dst VARCHAR(200) NOT NULL,"
                    + "dpt INT(100) NOT NULL,"
                    + "proto VARCHAR(10) NOT NULL,"
                    + "PRIMARY KEY(idLog))";

            Statement statement = connection.createStatement();
            statement.execute(newTable);
            System.out.println("Log table created");
        } else {
            System.out.println("Log table already created");
        }

        //Mirar si hi ha una taula on emmagatzemar la llista d'errors
        if (!tableExists(connection, ErrorTable)){

            String newTable =
                    "CREATE TABLE " + ErrorTable + " ("
                    + "idErr INT(64) NOT NULL AUTO_INCREMENT,"
                    + "numRow INT(64) NOT NULL,"
                    + "line LONGTEXT NOT NULL,"
                    + "PRIMARY KEY(idErr))";

            Statement statement = connection.createStatement();
            statement.execute(newTable);
            System.out.println("Error table created");

        } else {
            System.out.println("Error table already created");
        }

    }

    @Override
    //List: ASALogData
    public void insertData(List data) {



    }

    public boolean tableExists(Connection conn, String tableName) throws SQLException {

        boolean tExists = false;

        try (ResultSet rs = conn.getMetaData().getTables(null, null, tableName, null)){
            while (rs.next()){

                String tName = rs.getString("TABLE_NAME");
                if (tName != null & tName.equals(tableName)){
                    tExists = true;
                    break;
                }

            }
        }

        return tExists;

    }
}
