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
                    + "name VARCHAR(300) NOT NULL,"
                    + "start DATETIME NOT NULL,"
                    + "msg VARCHAR(500) NOT NULL,"
                    + "suser VARCHAR(100) NOT NULL,"
                    + "src VARCHAR(40) NOT NULL,"
                    + "spt INT(7) NOT NULL,"
                    + "dst VARCHAR(15) NOT NULL,"
                    + "dpt INT(7) NOT NULL,"
                    + "proto VARCHAR(5) NOT NULL,"
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

            //Filtrem les dades per tal de veure que no se sobresurtin del camp i ens donin error
            if (AsaData.get(i).getName().length() > 300 || AsaData.get(i).getMsg().length() > 500 || AsaData.get(i).getSuser().length() > 100
            || AsaData.get(i).getSrc().length() > 40 || AsaData.get(i).getDst().length() > 15 || AsaData.get(i).getProto().length() > 5) {

                //Si no és correcte algun camp, enviarem el log a la llista d'errors
                addErrorDao(i, AsaData.get(i).toString());

            //Si està tot correcte, es farà la inserció correctament a la taula ASA
            } else {

                String insert =
                        "INSERT INTO ASALogData (" +
                                "name, " +
                                "start, " +
                                "msg, " +
                                "suser, " +
                                "src, " +
                                "spt, " +
                                "dst, " +
                                "dpt, " +
                                "proto)" +
                                "VALUES (" +
                                "?, " +
                                "?, " +
                                "?, " +
                                "?, " +
                                "?, " +
                                "?, " +
                                "?, " +
                                "?, " +
                                "?) ";

                PreparedStatement pstmt = null;

                try {
                    pstmt = connection.prepareStatement(insert);

                    pstmt.setString(1, AsaData.get(i).getName());
                    pstmt.setString(2, AsaData.get(i).getStart().toString());
                    pstmt.setString(3, AsaData.get(i).getMsg());
                    pstmt.setString(4, AsaData.get(i).getSuser());
                    pstmt.setString(5, AsaData.get(i).getSrc());
                    pstmt.setString(6, String.valueOf(AsaData.get(i).getSpt()));
                    pstmt.setString(7, AsaData.get(i).getDst());
                    pstmt.setString(8, String.valueOf(AsaData.get(i).getDpt()));
                    pstmt.setString(9, AsaData.get(i).getProto());

                    pstmt.executeUpdate();
                    pstmt.close();

                } catch (Exception ignore){
                }

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
