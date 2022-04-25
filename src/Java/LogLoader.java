package Java;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

abstract class LogLoader <T>{

    private List<LoadError> errors = new ArrayList<>();

    public abstract List<T> load (File logFile) throws Exception;

    public void addErrorLog(int lineNum, String line){

        errors.add(new LoadError(lineNum, line));

    }

    public void showErrorList(){
        System.out.println("Línies amb errors: " + errors.size());
    }

}
