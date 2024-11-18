package Frontend;

import java.awt.*;

public class GridBaggy extends GridBagConstraints {
    public GridBaggy(){
    }

    public GridBaggy(int x, int y, int gridwith, int gridheight){
        super(x, y, gridwith, gridheight, 0.25, 0.25, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(1, 1, 1, 1), 0, 0);
    }
}

