package Backend;

import javax.swing.text.AttributeSet;
import javax.swing.text.Document;
import javax.swing.text.Element;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Waehrungsimport {
    public static void main (String[] args) throws Exception {
        // XML von Webseite herunterladen
        URL url = new URL("https://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml?4735e933a9965f6cde6bbe8fcb22b3de");
        InputStream in = url.openConnection().getInputStream();
        OutputStream out;
        String os = System.getProperty("os.name");
        if (os.contains("Wind")) {
            out = new FileOutputStream("src\\main\\java\\Backend\\ImportierteWaehrungen.xml");
        } else {
            out = new FileOutputStream("src/main/java/Backend/ImportierteWaehrungen.xml");
        }
        byte[] buffer = new byte[1024];
        for (int n; (n = in.read(buffer)) != -1; out.write(buffer, 0, n)) ;
        in.close();
        out.close();

        // File auslesen und relevante Zeilen in String Builder speichern
        File myFile;
        if (os.contains("Wind")) {
            myFile = new File("src\\main\\java\\Backend\\ImportierteWaehrungen.xml");
        } else {
            myFile = new File("src/main/java/Backend/ImportierteWaehrungen.xml");
        }
        ArrayList<Waehrungselement> elementsList = new ArrayList<>();
        try {
            Scanner myFileReader = new Scanner(myFile);
            while (myFileReader.hasNextLine()) {
                String line = myFileReader.nextLine();
                // Nur Währungszeilen ausgeben
                Pattern pattern = Pattern.compile("=[0-9']{1,8}");
                //Pattern pattern = Pattern.compile("<Cube currency='(USD||JPY||PLN||THB)' rate='[0-9]{1,100}.[0-9]{1,4}'");
                Matcher matcher = pattern.matcher(line);
                boolean matchFound = matcher.find();
                if (matcher.find()){
                    // Nach ' trennen
                    String[] splitted = line.split("'");
                    for(int i = 0; i < splitted.length; i++) {
                        //System.out.println("Teil " + i + ": " + splitted[i]);
                    }
                    String waehrung = splitted[1];
                    float kurs = Float.parseFloat(splitted[3]);
                    Waehrungselement element = new Waehrungselement(waehrung, kurs);
                    elementsList.add(element);
                    //System.out.println("Währung: " + waehrung + " Kurs: " + kurs);
                }
                // Hier werden mehr gleiche Elemente angezeigt, wie bei der Ausgabe darüber.
                for(int i = 0; i < elementsList.size(); i++){
                    System.out.println(elementsList.get(i));
                }
                // Kurs aus Element abspeichern, aber wie?
            }

        } catch (FileNotFoundException e) {
            System.out.println("Die Datei existiert nicht");
        }




        //System.out.println("Inhalt Builder: " + builder);




    }
}



/*
// File auslesen
        File myFile;
        if (os.contains("Wind")) {
            myFile = new File("src\\main\\java\\Backend\\ImportierteWaehrungen.xml");
        } else {
            myFile = new File("src/main/java/Backend/ImportierteWaehrungen.xml");
        }
        ArrayList<Element> elementsList = new ArrayList<>();

        try {
            Scanner myFileReader = new Scanner(myFile);
            while (myFileReader.hasNextLine()) {

                String line = myFileReader.nextLine();


                String[] splitted = line.split("'");

                for(int i = 0; i < splitted.length; i++) {
                    System.out.println("Teil " + i + ": " + splitted[i]);
                }
                /*Element element = new Element(splitted[0], splitted[1], splitted[2], splitted[3], splitted[4]);
                elementsList.add(element);

                for(int i = 0; i < elementsList.size(); i++){
                    System.out.println(elementsList.get(i));
                }










            }

                    } catch (FileNotFoundException e) {
        System.out.println("Die Datei existiert nicht");
        }

                }
 */