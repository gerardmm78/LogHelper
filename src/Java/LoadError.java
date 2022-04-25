package Java;

public class LoadError {

    private int lineNum;
    private String line;

    public LoadError(int lineNum, String line) {
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
}
