package Java;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ASALogLoader extends LogLoader {

    private List<ASALogData> log = new ArrayList<>();

    @Override
    public List<ASALogData> load(File logFile) throws Exception {

        String line = "";
        String splitBy = ",";
        int brLineNum = 0;

        BufferedReader br = new BufferedReader(new FileReader(logFile));

        String name;
        String date;
        LocalDateTime start;
        String msg;
        String suser;
        String src;
        int spt;
        String dst;
        int dpt;
        String proto;

        while ((line = br.readLine()) != null){

            String cisco[] = line.split(splitBy);


            if (cisco.length >= 19){

                name = cisco[5];
                date = cisco[6];
                msg = cisco[9];
                suser = cisco[10];
                src = cisco[11];
                dst = cisco[14];
                proto = cisco[17];

                if (date.length() == 27 && isInteger(cisco[12]) && isInteger(cisco[15])){

                    date = date.substring(0, date.length() - 8);
                    start = LocalDateTime.parse(date);

                    spt = Integer.parseInt(cisco[12]);
                    dpt = Integer.parseInt(cisco[15]);

                    ASALogData asa = new ASALogData();

                    asa.setAll(name, start, msg, suser, src, spt, dst, dpt, proto);

                    log.add(asa);

                } else {
                    addErrorLog(brLineNum, br.readLine());
                }

            }

            brLineNum++;

        }

        return log;

    }

    public static boolean isInteger(String str){

        try {
            int x = Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e){
            return false;
        }

    }

    public void ShowList() throws SQLException{

        System.out.println("Línies correctes: " + log.size());
        showErrorList();

        ASALogDAO logdao = new ASALogDAO();
        logdao.createTable();
        logdao.insertData(log);

        //for (int i = 0; i < log.size(); i++){
//
        //    System.out.println(log.get(i).getName());
//
        //}

    }

    public void data(File logFile) throws Exception {

        String line = "";
        String splitBy = ",";

        BufferedReader br = new BufferedReader(new FileReader(logFile));

        line = br.readLine();
        String cisco[] = line.split(splitBy);

        String date = cisco[6].substring(0, cisco[6].length() - 8);
        date = date.replace('T', ' ');

        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime start = LocalDateTime.parse(date, format);

        System.out.println(start);

    }

    public void check(File logFile) throws Exception {

        String line = "";
        String splitBy = ",";

        BufferedReader br = new BufferedReader(new FileReader(logFile));

        while ((line = br.readLine()) != null){

            String cisco[] = line.split(splitBy);

            String name = cisco[5];

            String date = cisco[6];
            date = date.substring(0, date.length() - 8);
            LocalDateTime start = LocalDateTime.parse(date);

            String msg = cisco[9];
            String suser = cisco[10];
            String src = cisco[11];
            int spt = Integer.parseInt(cisco[12]);
            String dst = cisco[14];
            int dpt = Integer.parseInt(cisco[15]);
            String proto = cisco[17];


            if (cisco.length >= 19){
                ASALogData asa = new ASALogData();

                asa.setAll(name, start, msg, suser, src, spt, dst, dpt, proto);

                System.out.println(asa.getName());
                System.out.println(asa.getStart());
                System.out.println(asa.getMsg());
                System.out.println(asa.getSuser());
                System.out.println(asa.getSrc());
                System.out.println(asa.getSpt());
                System.out.println(asa.getDst());
                System.out.println(asa.getDpt());
                System.out.println(asa.getProto());
            }

        }

    }

}
