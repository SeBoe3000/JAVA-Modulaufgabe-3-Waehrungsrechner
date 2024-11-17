package Backend;

public class Ergebnisse {
    Float eingabe;
    String eingabeKurs;
    Float wechselkurs;
    Float ausgabe;
    String ausgabeKurs;

    public Ergebnisse(Float eingabe, String eingabeKurs, Float wechselkurs, Float ausgabe, String ausgabeKurs) {
        this.eingabe = eingabe;
        this.eingabeKurs = eingabeKurs;
        this.wechselkurs = wechselkurs;
        this.ausgabe = ausgabe;
        this.ausgabeKurs = ausgabeKurs;
    }

    public Float getEingabe() {
        return eingabe;
    }

    public void setEingabe(Float eingabe) {
        this.eingabe = eingabe;
    }

    public String getEingabeKurs() {
        return eingabeKurs;
    }

    public void setEingabeKurs(String eingabeKurs) {
        this.eingabeKurs = eingabeKurs;
    }

    public Float getAusgabe() {
        return ausgabe;
    }

    public void setAusgabe(Float ausgabe) {
        this.ausgabe = ausgabe;
    }

    public String getAusgabeKurs() {
        return ausgabeKurs;
    }

    public void setAusgabeKurs(String ausgabeKurs) {
        this.ausgabeKurs = ausgabeKurs;
    }

    public Float getWechselkurs() {
        return wechselkurs;
    }

    public void setWechselkurs(Float wechselkurs) {
        this.wechselkurs = wechselkurs;
    }

    @Override
    public String toString(){
        String string = "Eingabe: %f %s Kurs: %f Ausgabe %f %s.";
        return String.format(string, this.eingabe, this.eingabeKurs, this.wechselkurs, this.ausgabe, this.ausgabeKurs);
    }
}
