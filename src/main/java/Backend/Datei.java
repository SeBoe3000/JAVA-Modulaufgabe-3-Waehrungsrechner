package Backend;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Datei {

    public static void createFile(String dateiname) throws Exception{
        boolean correct = false;
        File myFile = new File(dateiname);
        try {
            boolean datei = myFile.createNewFile();
        } catch (IOException e) {
            throw new Exception("Error");
        }
    }

    public static void leerenFile(String dateiname){
        File myFile = new File(dateiname);
        try{
            FileWriter meinSchreiberling = new FileWriter(dateiname, false);
            meinSchreiberling.write("");
            meinSchreiberling.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void writeFile(String dateiname, String text){
        File myFile = new File(dateiname);
        try{
            FileWriter meinSchreiberling = new FileWriter(dateiname, true);
            meinSchreiberling.write(text + "\n");
            meinSchreiberling.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}

