package Frontend;

import javax.swing.*;

public class WaehrungsrechnerGUI {
    private static final JFrame frame = new JFrame("Waehrungsrechner");


    private static void createGUI() {

    }

    private static void fixxedFrame() {
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
