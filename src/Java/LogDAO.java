package Java;

import java.sql.SQLException;
import java.util.List;

public interface LogDAO <T>{

    public abstract void createTable() throws SQLException;

    public abstract void insertData(List<T> data);

}
