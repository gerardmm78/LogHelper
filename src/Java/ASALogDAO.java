package Java;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ASALogDAO extends LogDAO {

    Connection connection;

    {
        try {
            connection = DriverManager.getConnection("jdbc:mariadb://172.16.0.100:3306/loghelperdb", "loghelper", "1234");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createTable() throws SQLException {

        String ASATable = "ASALogData";
        String ErrorTable = "DAOError";

        //Mirar si hi ha una taula on emmagatzemar la llista Asa log, per sinó crear-la
        if (!tableExists(connection, ASATable)){

            String newTable =
                    "CREATE TABLE " + ASATable + " ("
                    + "idLog INT(64) NOT NULL AUTO_INCREMENT,"
                    + "name TEXT NOT NULL,"
                    + "start DATETIME NOT NULL,"
                    + "msg TEXT NOT NULL,"
                    + "suser VARCHAR(100) NOT NULL,"
                    + "src TEXT NOT NULL,"
                    + "spt INT(100) NOT NULL,"
                    + "dst TEXT NOT NULL,"
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
    @SuppressWarnings({"unchecked", "deprecated"})
    public void insertData(List data){

        List<ASALogData> AsaData = data;

        for (int i = 0; i < AsaData.size(); i++){

            //Fem la comanda d'inserir valors en la Taula ASALogData de la base de dades
            String insert =
                    "INSERT ASALogData (name, start, msg, suser, src, spt, dst, dpt, proto)" +
                    " VALUES('" + AsaData.get(i).getName() + "', '" + AsaData.get(i).getStart()
                    + "', '" + AsaData.get(i).getMsg() + "', '" + AsaData.get(i).getSuser() + "', '" + AsaData.get(i).getSrc()
                    + "', '" + AsaData.get(i).getSpt() + "', '" + AsaData.get(i).getDst() + "', '" + AsaData.get(i).getDpt()
                    + "', '" + AsaData.get(i).getProto() + "');"
                    ;

            Statement statement = null;

            try {

                statement = connection.createStatement();
                statement.execute(insert);

            } catch (SQLException e) {

                addErrorDao(i, AsaData.get(i).toString());

            }

        }

        List<DAOError> newerr = errors;

        for (int i = 0; i < newerr.size(); i++){

            String line = newerr.get(i).getLine().replace("'", "");

            //Fem la comanda d'inserir valors en la Taula DAOError de la base de dades
            String insert =
                    "INSERT DAOError (numRow, line) VALUES('" + newerr.get(i).getLineNum() + "', '" + line + "');";

            Statement statement = null;

            try {
                statement = connection.createStatement();
                statement.execute(insert);
            } catch (SQLException e){
                e.printStackTrace();
            }

        }

        System.out.println("Logs created in the database");

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
