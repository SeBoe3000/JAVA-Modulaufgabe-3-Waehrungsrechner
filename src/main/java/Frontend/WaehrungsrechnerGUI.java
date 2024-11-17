package Frontend;

import Backend.Datei;
import Backend.Ergebnisse;
import Backend.Rechnungen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class WaehrungsrechnerGUI implements KeyListener {
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

    // Berechnungen ausgeben
    JButton safe_btn = new JButton("Dateiausgabe");
    EingabePanel dateiname = new EingabePanel("Dateiname: ");
    ArrayList<Ergebnisse> ErgebnisseList = new ArrayList<>();

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

        // Berechnungen ausgeben

        JPanel berechnung_panel = new JPanel();
        berechnung_panel.setLayout(new BoxLayout(berechnung_panel, BoxLayout.X_AXIS));
        berechnung_panel.add(dateiname);
        berechnung_panel.add(safe_btn);

        gbc.gridy = 6; // Spalte
        gbc.gridx = 0; // Zeile
        safe_btn.setEnabled(false); // Ausgabe nur möglich, wenn bereits Berechnungen durchgeführt
        dateiname.setEnabledFalse();
        panel.add(berechnung_panel, gbc);

        // KeyListener
        frame.addKeyListener(this);
        frame.setFocusable(true);

        eingabe.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed (java.awt.event.KeyEvent evt){
                if(evt.getKeyCode() == KeyEvent.VK_TAB) {
                    frame.setFocusable(true);
                }
            }
        });

        // Panel dem Frame hinzufügen
        frame.add(panel);
    }

    private void createHelp(){
        JDialog dialog = new JDialog(frame, "Hilfe", true);
        dialog.setSize(650, 250);
        dialog.setLocationRelativeTo(frame);
        dialog.setResizable(false);
        GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel text1 = new JLabel("Programm Währungsrechner");
        JLabel text2 = new JLabel("Im Programm Währungsrechner kann unter Eingabe vom");
        JLabel text3 = new JLabel("- Umzurechnenden Betrag,");
        JLabel text4 = new JLabel("- Eingabe- und Ausgabewährung oder eines manuell angegebenen Kurses");
        JLabel text5 = new JLabel("Beim Klick auf den = Button, das Ergebnis berechnet werden.");
        JLabel text6 = new JLabel("Wird die Checkbox 'bearbeiten?' markiert, wird");
        JLabel text7 = new JLabel("- die Eingabe- und Ausgabewährung gesperrt");
        JLabel text8 = new JLabel("- das Feld Wechselkurs geleert und für die Bearbeitung freigegeben.");
        JLabel text9 = new JLabel("Nach einer erfolgreichen Berechnung werden die Felder Dateiname und Dateiausgabe freigegeben.");
        JLabel text10 = new JLabel("Für die Dateiausgabe muss ein Dateiname angegeben werden.");
        JLabel text11 = new JLabel("Die ausgegebene Datei beinhaltet alle durchgeführten Berechnungen.");
        JLabel text12 = new JLabel("Die Datei wird in der IDE abgespeichert.");

        dialog.setLayout(gridbag);

        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        dialog.add(text1, gbc);
        gbc.gridy = 1;
        dialog.add(text2, gbc);
        gbc.gridy = 2;
        dialog.add(text3, gbc);
        gbc.gridy = 3;
        dialog.add(text4, gbc);
        gbc.gridy = 4;
        dialog.add(text5, gbc);
        gbc.gridy = 5;
        dialog.add(text6, gbc);
        gbc.gridy = 6;
        dialog.add(text7, gbc);
        gbc.gridy = 7;
        dialog.add(text8, gbc);
        gbc.gridy = 8;
        dialog.add(text9, gbc);
        gbc.gridy = 9;
        dialog.add(text10, gbc);
        gbc.gridy = 10;
        dialog.add(text11, gbc);
        gbc.gridy = 11;
        dialog.add(text12, gbc);
        gbc.gridy = 12;
        dialog.setVisible(true);
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
                    wechselkurs.setTextField("");
                    wechselkurs.setEnabledTrue();
                    setEingabebuttonEnabled(false);
                    setAusgabebuttonEnabled(false);
                } else {
                    wechselkurs.setEnabledFalse();
                    changeWechselkurs();
                    setEingabebuttonEnabled(true);
                    setAusgabebuttonEnabled(true);
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

        // Berechnung durchführen
        ActionListener rechnen = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Eingabe und Kurs prüfen, in Zahl umwandeln und rechnen
                float eingabeZahl = 0F;
                float eingabeKurs = 0F;

                if(checkValues(eingabe, "Integer", "Im Feld wurde Umzurechnender Betrag keine Zahl eingetragen.")){
                    eingabeZahl = Float.parseFloat(eingabe.getTextfield());
                }
                if(checkValues(wechselkurs, "Integer", "Im Feld Wechselkurs wurde keine Zahl eingetragen.")){
                    eingabeKurs = Float.parseFloat(wechselkurs.getTextfield());
                }

                try{
                    float ausgabeZahl = Rechnungen.ermittelnAusgabe(eingabeZahl,eingabeKurs);
                    if (ausgabeZahl == 0F) {
                        // Da die Felder initialisiert werden, wird bei einem Fehler sonst der Wert 0 angezeigt:
                        ausgabe.setTextField("");
                    } else {
                        ausgabe.setTextField("" + ausgabeZahl);
                        String eingabe = RadioButton.getTextofRadiobutton(eingabe_group);
                        String ausgabe = RadioButton.getTextofRadiobutton(ausgabe_group);
                        // Objekt erzeugen und abspeichern in ArrayList für die spätere Ausgabe
                        Ergebnisse ergebnis = new Ergebnisse(eingabeZahl, eingabe, eingabeKurs, ausgabeZahl, ausgabe);
                        ErgebnisseList.add(ergebnis);
                        // Dateiname und Button für Dateiausgabe verwaltbar machen
                        dateiname.setEnabledTrue();
                        safe_btn.setEnabled(true);
                        //System.out.println(ergebnis);
                    }
                } catch (Exception exec){
                    JOptionPane.showMessageDialog(null, "Das Ergebnis ist nicht darstellbar, da Infinity", "Fehler", JOptionPane.ERROR_MESSAGE);
                }

            }
        };
        calc_btn.addActionListener(rechnen);


        ActionListener dateiausgabe = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String datei = "";
                if (checkValues(dateiname, "String", "Es wurde kein Dateiname angegeben")) {
                    datei = dateiname.getTextfield();

                    // Datei erstellen und leeren
                    try{
                        Datei.createFile(datei);
                    } catch (Exception exec){
                        JOptionPane.showMessageDialog(null, "Allgemeiner Fehler beim erstellen der Datei", "Fehler", JOptionPane.ERROR_MESSAGE);
                    }
                    Datei.leerenFile(datei);

                    // Datei befüllen
                    for (int i = 0; i < ErgebnisseList.size(); i++) {
                        // Element aus der Liste in einen String umwandeln und dann an writeFile übergeben
                        String text = ErgebnisseList.get(i).toString();
                        Datei.writeFile(datei, text);
                    }
                    // Nachricht ausgeben
                    JOptionPane.showMessageDialog(null, "Bitte Programm schließen, um Datei zu erzeugen.");
                }




                // Systemausgabe zum prüfen
                /*for (int i = 0; i < ErgebnisseList.size(); i++) {
                    System.out.println(ErgebnisseList.get(i));
                }*/
            }
        };
        safe_btn.addActionListener(dateiausgabe);

    }

    // Wechselkursermittlung, wenn Eingabe- und Ausgabekurs angegeben sind
    public void changeWechselkurs (){
        String eingabe = RadioButton.getTextofRadiobutton(eingabe_group);
        String ausgabe = RadioButton.getTextofRadiobutton(ausgabe_group);

        if (!eingabe.equals("") && !ausgabe.equals("") && eingabe != ausgabe){
            //System.out.println("Eingabe- und Ausgabekurs sind unterschiedlich");
            Float wechselkursBerechnet = Rechnungen.ermittelnWechselkurs(eingabe, ausgabe);
            wechselkurs.setTextField("" + wechselkursBerechnet);

        } else if (!eingabe.equals("") && !ausgabe.equals("") && eingabe == ausgabe) {
            //System.out.println("Eingabe- und Ausgabekurs sind identisch");
            wechselkurs.setTextField("1");
        }
    }

    // Prüfung auf gültige Eingaben
    public boolean checkValues(EingabePanel input, String checkArt, String fehlernachricht){
        String check = input.getTextfield();
        boolean check1 = false;
        if(checkArt == "Integer") {
            check1 = EingabenCheck.isValidIntString(check);
            // Ergebnis löschen bei zuvor durchgeführter Rechnung
            ausgabe.setTextField("");
        } else if (checkArt == "String") {
            check1 = EingabenCheck.isValidString(check);
        }
        // Text für Fehlermeldung
        String text = fehlernachricht;
        String title = "Fehler";

        // Bei korrekter Eingabe (z.B. nach Fehler) Schriftfarbe zurückändern.
        input.removeError();

        if (!(check1)){
            // Beim Fehler die Schriftfarbe auf rotändern.
            input.setError();
            JOptionPane.showMessageDialog(null, text, title, JOptionPane.ERROR_MESSAGE);
            return false;

        } else {
            return true;
        }
    }


    public void setEingabebuttonEnabled(boolean truefalse) {
        if (truefalse) {
            ein_euro_rbtn.setEnabled(true);
            ein_usd_rbtn.setEnabled(true);
            ein_bath_rbtn.setEnabled(true);
            ein_yen_rbtn.setEnabled(true);
            ein_zloty_rbtn.setEnabled(true);
        } else {
            ein_euro_rbtn.setEnabled(false);
            ein_usd_rbtn.setEnabled(false);
            ein_bath_rbtn.setEnabled(false);
            ein_yen_rbtn.setEnabled(false);
            ein_zloty_rbtn.setEnabled(false);
        }
    }
    public void setAusgabebuttonEnabled(boolean truefalse) {
        if (truefalse) {
            aus_euro_rbtn.setEnabled(true);
            aus_usd_rbtn.setEnabled(true);
            aus_bath_rbtn.setEnabled(true);
            aus_yen_rbtn.setEnabled(true);
            aus_zloty_rbtn.setEnabled(true);
        } else {
            aus_euro_rbtn.setEnabled(false);
            aus_usd_rbtn.setEnabled(false);
            aus_bath_rbtn.setEnabled(false);
            aus_yen_rbtn.setEnabled(false);
            aus_zloty_rbtn.setEnabled(false);
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

    @Override
    public void keyTyped(KeyEvent k){
    }

    @Override
    public void keyPressed(KeyEvent k){
    }

    @Override
    public void keyReleased(KeyEvent k){
        switch (k.getKeyCode()){
            case KeyEvent.VK_F1: {
                createHelp();
                break;
            }
        }

    }
}

