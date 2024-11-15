package Frontend;

import javax.swing.*;
import java.awt.*;

public class WaehrungsrechnerGUI {
    private static final JFrame frame = new JFrame("Waehrungsrechner");


    private static void createGUI() {

    }

    private static void fixxedFrame() {
        // Größe vom Fenster auf Hälte der Bildschirmgröße in die Mitte setzen
        Dimension dim = new Dimension(1920, 1080);
        dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(dim.width/2, dim.height/2);
        frame.setLocation(dim.width/4, dim.height/4);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private static void buttonListener() {
    }


    public void main(){
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createGUI();
                buttonListener();
                fixxedFrame();
            }
        });
    }

}
