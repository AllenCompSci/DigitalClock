package generic.graphics;

import java.util.ArrayList;

import static generic.graphics.DDR.COLUMNSHIFT;
import static generic.graphics.DDR.UNIT;

/**
 * Created by theshrewedshrew on 3/31/17.
 */
public class DDR {
    enum Direction {LEFT, DOWN, RIGHT, UP};
    enum COLUMN{RED_COL, YELLOW_COL, GREEN_COL, BLUE_COL};
    ArrayList<Arrow> collection;
    final static int UNIT = 15;
    static int COLUMNSHIFT = UNIT * 33;
    static int ZONE = DigitalTimer.getHeight() - (int)(UNIT * 8 * 1.5);

    public DDR(){

    }

}

