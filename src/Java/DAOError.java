package Java;

public class DAOError {

    private int lineNum;
    private  String line;

    public DAOError(int lineNum, String line) {
        this.lineNum = lineNum;
        this.line = line;
    }

    public int getLineNum() {
        return lineNum;
    }

    public void setLineNum(int lineNum) {
        this.lineNum = lineNum;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    @Override
    public String toString() {
        return "DAOError{" +
                "lineNum=" + lineNum +
                ", line='" + line + '\'' +
                '}';
    }
}
