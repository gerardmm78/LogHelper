package Java;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class LogDAO <T>{

    List<DAOError> errors = new ArrayList<>();

    public abstract void createTable() throws SQLException;

    public abstract void insertData(List<T> data);

    public void addErrorDao(int lineNum, String line){
        errors.add(new DAOError(lineNum, line));
    }

    public void showErrorDao(){
        System.out.println("Linies amb errors DAO: " + errors.size());
    }

}
