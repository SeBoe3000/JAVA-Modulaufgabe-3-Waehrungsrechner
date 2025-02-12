package Backend;

// Für den automatischen Import der Währungen. Ist aber nicht fertig.
public class Waehrungselement {
    private String waehrung;
    private float wechselkurs;


    public Waehrungselement(){}

    public Waehrungselement(String waehrung, float wechselkurs) {
        this.waehrung = waehrung;
        this.wechselkurs = wechselkurs;
    }

    @Override
    public String toString(){
        return "" + waehrung + "; " + wechselkurs;
    }
}

