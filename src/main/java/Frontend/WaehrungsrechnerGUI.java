package Frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WaehrungsrechnerGUI {
    private static final JFrame frame = new JFrame("Waehrungsrechner");

    // Eingabebetrag
    EingabePanel eingabe = new EingabePanel("Umzurechnender Betrag: ");

    // Eingabewährung
    JRadioButton ein_euro_rbtn = new JRadioButton("Euro");
    JRadioButton ein_usd_rbtn = new JRadioButton("USD");
    JRadioButton ein_bath_rbtn = new JRadioButton("Bath");
    JRadioButton ein_yen_rbtn = new JRadioButton("Yen");
    JRadioButton ein_zloty_rbtn = new JRadioButton("Złoty");
    JLabel eingabewaehrung_lbl = new JLabel("Eingabewaehrung: ");
    ButtonGroup eingabe_group = new ButtonGroup();

    // Ausgabewährung
    JRadioButton aus_euro_rbtn = new JRadioButton("Euro");
    JRadioButton aus_usd_rbtn = new JRadioButton("USD");
    JRadioButton aus_bath_rbtn = new JRadioButton("Bath");
    JRadioButton aus_yen_rbtn = new JRadioButton("Yen");
    JRadioButton aus_zloty_rbtn = new JRadioButton("Złoty");
    JLabel ausgabewaehrung_lbl = new JLabel("Ausgabewaehrung: ");
    ButtonGroup ausgabe_group = new ButtonGroup();

    // Wechselkurs
    EingabePanel wechselkurs = new EingabePanel("Wechselkurs: ");
    JCheckBox wechselkurs_chb = new JCheckBox("bearbeiten?");

    // Berechnen-Button
    JButton calc_btn = new JButton("=");

    // Ergebnis
    EingabePanel ausgabe = new EingabePanel("Ergebnis: ");

    private void createGUI() {
        JPanel panel = new JPanel();
        // GridBagLayout
        GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        panel.setLayout(gridbag);

        // Feld Eingabe hinzufügen
        gbc.gridx = 0; // Spalte
        gbc.gridy = 0; // Zeile
        gbc.weightx = 0.1;
        gbc.weighty = 0.1;
        gbc.fill = GridBagConstraints.CENTER;
        panel.add(eingabe, gbc);

        // Feld Eingabewährung hinzufügen
        eingabe_group.add(ein_euro_rbtn);
        eingabe_group.add(ein_usd_rbtn);
        eingabe_group.add(ein_bath_rbtn);
        eingabe_group.add(ein_yen_rbtn);
        eingabe_group.add(ein_zloty_rbtn);

        JPanel eingabe_radio_panel = new JPanel();
        eingabe_radio_panel.setLayout(new BoxLayout(eingabe_radio_panel, BoxLayout.X_AXIS));
        eingabe_radio_panel.add(ein_euro_rbtn);
        eingabe_radio_panel.add(ein_usd_rbtn);
        eingabe_radio_panel.add(ein_bath_rbtn);
        eingabe_radio_panel.add(ein_yen_rbtn);
        eingabe_radio_panel.add(ein_zloty_rbtn);

        JPanel eingabe_panel = new JPanel();
        eingabe_panel.setLayout(new BoxLayout(eingabe_panel, BoxLayout.X_AXIS));
        eingabe_panel.add(eingabewaehrung_lbl);
        eingabe_panel.add(eingabe_radio_panel);

        gbc.gridy = 1; // Spalte
        gbc.gridx = 0; // Zeile
        panel.add(eingabe_panel, gbc);

        // Feld Ausgabewährung hinzufügen
        ausgabe_group.add(aus_euro_rbtn);
        ausgabe_group.add(aus_usd_rbtn);
        ausgabe_group.add(aus_bath_rbtn);
        ausgabe_group.add(aus_yen_rbtn);
        ausgabe_group.add(aus_zloty_rbtn);

        JPanel ausgabe_radio_panel = new JPanel();
        ausgabe_radio_panel.setLayout(new BoxLayout(ausgabe_radio_panel, BoxLayout.X_AXIS));
        ausgabe_radio_panel.add(aus_euro_rbtn);
        ausgabe_radio_panel.add(aus_usd_rbtn);
        ausgabe_radio_panel.add(aus_bath_rbtn);
        ausgabe_radio_panel.add(aus_yen_rbtn);
        ausgabe_radio_panel.add(aus_zloty_rbtn);

        JPanel ausgabe_panel = new JPanel();
        ausgabe_panel.setLayout(new BoxLayout(ausgabe_panel, BoxLayout.X_AXIS));
        ausgabe_panel.add(ausgabewaehrung_lbl);
        ausgabe_panel.add(ausgabe_radio_panel);

        gbc.gridy = 2; // Spalte
        gbc.gridx = 0; // Zeile
        panel.add(ausgabe_panel, gbc);

        // Feld Wechselkurs hinzufügen
        JPanel wechselkurs_panel = new JPanel();
        wechselkurs_panel.setLayout(new BoxLayout(wechselkurs_panel, BoxLayout.X_AXIS));
        wechselkurs_panel.add(wechselkurs);
        wechselkurs_panel.add(wechselkurs_chb);
        wechselkurs.setEnabledFalse(); // Wechselkurs zu Beginn für die Verwaltung sperren

        gbc.gridy = 3; // Spalte
        gbc.gridx = 0; // Zeile
        panel.add(wechselkurs_panel, gbc);


        // Feld Berechnen hinzufügen
        gbc.gridy = 4; // Spalte
        gbc.gridx = 0; // Zeile
        calc_btn.setPreferredSize(new Dimension(400,50));
        panel.add(calc_btn, gbc);

        // Feld Ausgabe Ergebnis hinzufügen
        gbc.gridy = 5; // Spalte
        gbc.gridx = 0; // Zeile
        ausgabe.setEnabledFalse(); // Ausgabe generell für die Verwaltung sperren
        panel.add(ausgabe, gbc);

        // Panel dem Frame hinzufügen
        frame.add(panel);
    }

    private void fixxedFrame() {
        // Größe vom Fenster auf Hälte der Bildschirmgröße in die Mitte setzen
        Dimension dim = new Dimension(1920, 1080);
        dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(dim.width/2, dim.height/2);
        frame.setLocation(dim.width/4, dim.height/4);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private void buttonListener() {
        /* To-Do's:
        - Feld: calc_btn --> Berechnung durchführen.

        Für Berechnung:
        - Eingabe, Eingabekurs, Ausgabekurs und Wechselkurs (wenn bearbeitbar) muss eingegeben sein.
        - Eingabekurs darf nicht gleich Ausgabekurs sein
        - Bei Fehler ggf. Felder rot umranden
        */

        /* Wenn Checkbox hinter Wechselkurs markiert, dann
        - Feld Wechselkurs editierbar machen
        - Wechselkurs leeren
        - Felder Eingabe- und Ausgabewährung sperren
        Wenn Checkbox hinter Wechselkurs demarkiert, dann
        - Feld Wechselkurs sperren
        - Felder Eingabe- und Ausgabewährung aktivieren
        - Wechselkurs ermitteln, wenn Eingabe- und Ausgabekurs angegeben */
        ActionListener wechselkurs_ausgrauen = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(wechselkurs_chb.isSelected()){
                    wechselkurs.setTextField(" ");
                    wechselkurs.setEnabledTrue();
                    ein_euro_rbtn.setEnabled(false);
                    ein_usd_rbtn.setEnabled(false);
                    ein_bath_rbtn.setEnabled(false);
                    ein_yen_rbtn.setEnabled(false);
                    ein_zloty_rbtn.setEnabled(false);
                    aus_euro_rbtn.setEnabled(false);
                    aus_usd_rbtn.setEnabled(false);
                    aus_bath_rbtn.setEnabled(false);
                    aus_yen_rbtn.setEnabled(false);
                    aus_zloty_rbtn.setEnabled(false);
                } else {
                    wechselkurs.setEnabledFalse();
                    changeWechselkurs();
                    ein_euro_rbtn.setEnabled(true);
                    ein_usd_rbtn.setEnabled(true);
                    ein_bath_rbtn.setEnabled(true);
                    ein_yen_rbtn.setEnabled(true);
                    ein_zloty_rbtn.setEnabled(true);
                    aus_euro_rbtn.setEnabled(true);
                    aus_usd_rbtn.setEnabled(true);
                    aus_bath_rbtn.setEnabled(true);
                    aus_yen_rbtn.setEnabled(true);
                    aus_zloty_rbtn.setEnabled(true);
                }
            }
        };
        wechselkurs_chb.addActionListener(wechselkurs_ausgrauen);

        /* Bei Änderung vom Eingabe- oder Ausgabekurs den Wechselkurs eintragen, wenn
        - Eingabe- und Ausgabekurs angegeben sind (Bestandteil von Methode changeWechselkurs)
         - Wechselkurs nicht veränderbar ist */
        ActionListener wechselkurs_default = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!wechselkurs_chb.isSelected()) {
                    changeWechselkurs();
                }
            }
        };
        ein_euro_rbtn.addActionListener(wechselkurs_default);
        ein_usd_rbtn.addActionListener(wechselkurs_default);
        ein_bath_rbtn.addActionListener(wechselkurs_default);
        ein_yen_rbtn.addActionListener(wechselkurs_default);
        ein_zloty_rbtn.addActionListener(wechselkurs_default);
        aus_euro_rbtn.addActionListener(wechselkurs_default);
        aus_usd_rbtn.addActionListener(wechselkurs_default);
        aus_bath_rbtn.addActionListener(wechselkurs_default);
        aus_yen_rbtn.addActionListener(wechselkurs_default);
        aus_zloty_rbtn.addActionListener(wechselkurs_default);
    }

    // Wechselkursermittlung, wenn Eingabe- und Ausgabekurs angegeben sind
    public void changeWechselkurs (){
        String eingabe = RadioButton.getTextofRadiobutton(eingabe_group);
        String ausgabe = RadioButton.getTextofRadiobutton(ausgabe_group);

        if (!eingabe.equals("") && !ausgabe.equals("") && eingabe != ausgabe){
            System.out.println("Eingabe- und Ausgabekurs sind unterschiedlich");
            // Wechselkurs ermitteln und in Feld eintragen für Euro, USD, Bath, Yen, Złoty;
            wechselkurs.setTextField("Umrechnung");

        } else if (!eingabe.equals("") && !ausgabe.equals("") && eingabe == ausgabe) {
            System.out.println("Eingabe- und Ausgabekurs sind identisch");
            wechselkurs.setTextField("1");
        }
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
