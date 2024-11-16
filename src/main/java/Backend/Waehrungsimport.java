package Backend;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

public class Waehrungsimport {
    public static void main (String[] args) throws Exception {
        URL url = new URL("https://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml?4735e933a9965f6cde6bbe8fcb22b3de");
        InputStream in = url.openConnection().getInputStream();
        OutputStream out;
        String os = System.getProperty("os.name");
        if(os.contains("Wind")){
            out = new FileOutputStream("src\\main\\java\\Backend\\ImportierteWaehrungen.xml");
        } else {
            out = new FileOutputStream("src/main/java/Backend/ImportierteWaehrungen.xml");
        }
        byte[] buffer = new byte[1024];
        for (int n;(n = in.read(buffer)) != -1;out.write(buffer, 0, n));
        in.close();
        out.close();
    }
}
