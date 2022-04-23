package Java;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Main {

    public static void main (String[] args) throws Exception{
        Scanner in = new Scanner(System.in);

        String line = "";
        String splitBy = ",";

        String file = null;
        BufferedReader br = null;

        System.out.print("Passa la ruta absoluta del fitxer CSV: ");

        while (file == null){

            //El que fem aquí és donar el fitxer que volem guardar els elements, perquè sigui de manera dinàmica i no estàtica
            //Haurem de passar el "Path" del fitxer csv en la línia d'ordres
            file = in.nextLine();

            //Si el fitxer passat és CSV, guardarem les dades en el BufferReader
            if (file.endsWith(".csv")){

                //I aquí guardarem el BufferReader que ens permitirà llegir i manipular el document
                br = new BufferedReader(new FileReader(file));

            } else if (file.equals("0")){

                //Si volem sortir de l'aplicació, indicant un 0 en el fitxer, se'ns sortirà de la terminal
                Runtime.getRuntime().exec("taskkill /f /im cmd.exe");

            } else {

                //Si el fitxer no és l'indicat, fitxer serà null, i tornarà a demanar un fitxer
                System.out.println("Fitxer no compatible. Torna a intentar-ho");
                file = null;

            }

        }

        while ((line = br.readLine()) != null){

            String cisco[] = line.split(splitBy);

            //Ja que en alguna fila pot faltar algun camp o hi poden haver missatges amb comes, ens saltem tals files
            //amb menys de 19 camps, perquè així no donarà cap error
            if (cisco.length >= 19){

                System.out.println(
                        "Name :" + cisco[5] +
                                "\nStart: " + cisco[6] +
                                "\nMsg: " + cisco[9] +
                                "\nSuser: " + cisco[10] +
                                "\nSrc: " + cisco[11] +
                                "\nSpt: " + cisco[12] +
                                "\nDst: " + cisco[14] +
                                "\nDpt: " + cisco[15] +
                                "\nProto: " + cisco[17] + "\n"
                );
            }

        }

    }

}