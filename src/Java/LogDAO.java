package Java;

import java.util.List;

public interface LogDAO <T>{

    public abstract void createTable();

    public abstract void insertData(List<T> data);

}
