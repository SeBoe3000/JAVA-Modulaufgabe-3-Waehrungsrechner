package Frontend;

public class EingabenCheck {
    public static boolean isValidIntString(String eingabe){
        boolean isValid = true;

        // Nur Zahlen und Punkte sind zulässige Zeichen
        for(int i = 0; i < eingabe.length(); i++){
            if(!((eingabe.charAt(i) >= '0' && eingabe.charAt(i) <= '9')||eingabe.charAt(i) == '.')){
                isValid = false;
                break;
            }
        }

        // Nur ein Punkt darf vorhanden sein.
        // Es muss mindestens eine Zahl zwischen 1 und 9 angegeben sein. (Dadurch auch Überprüfung auf nicht leerer Wert)
        Integer anzahlPunkte = 0;
        Integer anzahlZahl = 0;
        for(int i = 0; i < eingabe.length(); i++){
            if((eingabe.charAt(i) >= '1' && eingabe.charAt(i) <= '9')){
                anzahlZahl ++;
            }
            if(eingabe.charAt(i) == '.'){
                anzahlPunkte ++;
            }
        }
        //System.out.println("anzahlPunkte " + anzahlPunkte + " anzahlZahl " + anzahlZahl);
        if (anzahlPunkte > 1 || anzahlZahl == 0){
            isValid = false;
        }

        return isValid;
    }
}

