package Backend;

public class Rechnungen {

    public static Float ermittelnWechselkurs(String eingabe, String ausgabe) {
        // Wechselkurse von Euro aus:
        Float wechselkursEuroUSD = 1.05F;
        Float wechselkursEuroBath = 36.70F;
        Float wechselkursEuroYen = 162.77F;
        Float wechselkursEuroZloty = 4.32F;
        Float wechselkursZahl = 0F;

        // System.out.println(eingabe);
        if(eingabe.equals("Euro")) {
            wechselkursZahl = 1F;
        } else if(eingabe.equals("USD")) {
            wechselkursZahl = 1 / wechselkursEuroUSD;
        } else if (eingabe.equals("Bath")) {
            wechselkursZahl = 1 / wechselkursEuroBath;
        } else if (eingabe.equals("Yen")) {
            wechselkursZahl = 1 / wechselkursEuroYen;
        } else if (eingabe.equals("Złoty")) {
            wechselkursZahl = 1 / wechselkursEuroZloty;
        }

        if(ausgabe.equals("USD")) {
            wechselkursZahl = wechselkursZahl * wechselkursEuroUSD;
        } else if (ausgabe.equals("Bath")) {
            wechselkursZahl = wechselkursZahl * wechselkursEuroBath;
        } else if (ausgabe.equals("Yen")) {
            wechselkursZahl = wechselkursZahl * wechselkursEuroYen;
        } else if (ausgabe.equals("Złoty")) {
            wechselkursZahl = wechselkursZahl * wechselkursEuroZloty;
        }
        return wechselkursZahl;
    }


}
