package Frontend;

import javax.swing.*;
import java.util.Enumeration;

public class RadioButton {

    public static String getTextofRadiobutton (ButtonGroup group) {
        String calculate_sign = "";
        for (Enumeration<AbstractButton> buttons = group.getElements(); buttons.hasMoreElements(); ) {
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()) {
                calculate_sign = button.getText();
            }
        } return calculate_sign;
    }
}
