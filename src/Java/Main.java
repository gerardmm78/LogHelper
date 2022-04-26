package Java;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main (String[] args) throws Exception{
        Scanner in = new Scanner(System.in);

        System.out.println("Prem 1 per a emmagatzemar dades");
        System.out.println("Prem 0 per a tancar l'aplicació");
        System.out.println("--------------------------------");
        System.out.println();
        int num;

        do {
            num = in.nextInt();

            if (num == 1) setData();
            else if (num == 0) Runtime.getRuntime().exec("taskkill /f /im cmd.exe");

        } while (num != 0);

    }

    public static void setData() throws Exception{
        Scanner in = new Scanner(System.in);

        String file = null;
        File ciscoFile = null;

        System.out.print("A continuació insereix el fitxer a emmagatzemar: ");

        while (file == null){

            file = in.nextLine();

            if (file.endsWith(".csv")){

                ASALogLoader asa = new ASALogLoader();
                asa.load(new File(file));
                asa.ShowList();

            } else {

                System.out.println("Fitxer no compatible. Torna a intentar-ho");
                file = null;

            }

        }

    }

}
