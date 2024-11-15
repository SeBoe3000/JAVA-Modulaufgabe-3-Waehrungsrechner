package Frontend;

public class EingabenCheck {
    public static boolean isValidIntString(String eingabe){
        boolean isValid = true;
        for(int i = 0; i < eingabe.length(); i++){
            if(!(eingabe.charAt(i) >= '0' && eingabe.charAt(i) <= '9')){
                isValid = false;
                break;
            }
        }
        if(eingabe.equals("")){
            return false;
        }
        return isValid;
    }
}

