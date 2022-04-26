package Java;

import java.time.LocalDateTime;

public class ASALogData implements LogData{

    private String name;
    private LocalDateTime start;
    private String msg;
    private String suser;
    private String src;
    private int spt;
    private String dst;
    private  int dpt;
    private String proto;

    public ASALogData(){

    }

    public ASALogData(String name, LocalDateTime start, String msg, String suser, String src, int spt, String dst, int dpt, String proto) {
        this.name = name;
        this.start = start;
        this.msg = msg;
        this.suser = suser;
        this.src = src;
        this.spt = spt;
        this.dst = dst;
        this.dpt = dpt;
        this.proto = proto;
    }

    public ASALogData(String name){
        this.name = name;
    }

    public void setAll(String name, LocalDateTime start, String msg, String suser, String src, int spt, String dst, int dpt, String proto){
        this.name = name;
        this.start = start;
        this.msg = msg;
        this.suser = suser;
        this.src = src;
        this.spt = spt;
        this.dst = dst;
        this.dpt = dpt;
        this.proto = proto;
    }

    public void getAll(){
        System.out.println(this.name + ", " + this.start + ", " + this.msg + ", " + this.suser + ", " + this.src + ", " + this.spt+ ", " + this.dst + ", " + this.dpt + ", " + this.proto);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getSuser() {
        return suser;
    }

    public void setSuser(String suser) {
        this.suser = suser;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public int getSpt() {
        return spt;
    }

    public void setSpt(int spt) {
        this.spt = spt;
    }

    public String getDst() {
        return dst;
    }

    public void setDst(String dst) {
        this.dst = dst;
    }

    public int getDpt() {
        return dpt;
    }

    public void setDpt(int dpt) {
        this.dpt = dpt;
    }

    public String getProto() {
        return proto;
    }

    public void setProto(String proto) {
        this.proto = proto;
    }

    @Override
    public String toString() {
        return "ASALogData{" +
                "name='" + name + '\'' +
                ", start=" + start +
                ", msg='" + msg + '\'' +
                ", suser='" + suser + '\'' +
                ", src='" + src + '\'' +
                ", spt=" + spt +
                ", dst='" + dst + '\'' +
                ", dpt=" + dpt +
                ", proto='" + proto + '\'' +
                '}';

    }
}
