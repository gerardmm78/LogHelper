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

        //String line = "";
        //String splitBy = ",";

        String file = null;
        BufferedReader br = null;

        System.out.print("Passa la ruta absoluta del fitxer CSV: ");

        while (file == null){

            //El que fem aquí és donar el fitxer que volem guardar els elements, perquè sigui de manera dinàmica i no estàtica
            //Haurem de passar el "Path" del fitxer csv en la línia d'ordres
            file = in.nextLine();

            //Si el fitxer passat és CSV, guardarem les dades en el BufferReader
            if (file.endsWith(".csv")){

                ASALogLoader asa = new ASALogLoader();
                asa.load(new File(file));
                asa.ShowList();
                //asa.check(new File(file));

            } else if (file.equals("0")){

                //Si volem sortir de l'aplicació, indicant un 0 en el fitxer, se'ns sortirà de la terminal
                Runtime.getRuntime().exec("taskkill /f /im cmd.exe");

            } else {

                //Si el fitxer no és l'indicat, fitxer serà null, i tornarà a demanar un fitxer
                System.out.println("Fitxer no compatible. Torna a intentar-ho");
                file = null;

            }

        }

    }

}
