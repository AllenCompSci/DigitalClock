package generic.graphics;

import java.awt.*;

import static generic.graphics.DDR.COLUMNSHIFT;
import static generic.graphics.DDR.UNIT;

public class Arrow{
    int X, Y;
    int dXCoors[];
    int dYCoors[];
    int XCoor[];
    int YCoor[];
    final int numPoints = 7;
    Color Outline, Draw;
    private boolean toDelete;
    public Arrow(DDR.Direction point, DDR.COLUMN position){
        toDelete = false;
        Y = 0;
        X = 0;
        dXCoors = new int [7];
        dYCoors = new int [7];
        XCoor = new int [7];
        YCoor = new int [7];
        switch (position) {
            case BLUE_COL:
                X += 10 * UNIT;
            case GREEN_COL:
                X += 10 * UNIT;
            case YELLOW_COL:
                X += 10 * UNIT;
            case RED_COL:
                X += COLUMNSHIFT;
        }
        // creates a list of the change in the X and Y as an array
        for (int i = 0; i < 7; i++) {
            dXCoors[i] = dYCoors[i] = UNIT;
            // BASELINE REFERENCE EVERYTHING IN TERMS OF UNIT
        }
        switch (point) {
            /// EVERY dXCoor[0] is one unit shifted so any one unit shifts are unnecessary to express
            case LEFT:
                // skipped
                dYCoors[0] *= 4;
                dXCoors[1] *= 3;
                // skipped
                dXCoors[2] *= 3;
                dYCoors[2] *= 3;
                dXCoors[3] *= 7;
                dYCoors[3] *= 3;
                dXCoors[4] *= 7;
                dYCoors[4] *= 5;
                dXCoors[5] *= 3;
                dYCoors[5] *= 5;
                dXCoors[6] *= 3;
                dYCoors[6] *= 7;
                break;
            case DOWN:
                // skipped
                dYCoors[0] *= 5;
                dXCoors[1] *= 3;
                dYCoors[1] *= 5;
                dXCoors[2] *= 3;
                dYCoors[2] *= 2;
                dXCoors[3] *= 5;
                dYCoors[3] *= 2;
                dXCoors[4] *= 5;
                dYCoors[4] *= 5;
                dXCoors[5] *= 7;
                dYCoors[5] *= 5;
                dXCoors[6] *= 4;
                dYCoors[6] *= 7;
                break;
            case RIGHT:
                // skipped
                dYCoors[0] *= 3;
                dXCoors[1] *= 5;
                dYCoors[1] *= 3;
                dXCoors[2] *= 5;
                // skipped
                dXCoors[3] *= 7;
                dYCoors[3] *= 4;
                dXCoors[4] *= 5;
                dYCoors[4] *= 7;
                dXCoors[5] *= 5;
                dYCoors[5] *= 5;
                // skipped
                dYCoors[6] *= 5;
                break;
            case UP:
                // skipped
                dYCoors[0] *= 3;
                dXCoors[1] *= 4;
                // skipped
                dXCoors[2] *= 7;
                dYCoors[2] *= 3;
                dXCoors[3] *= 5;
                dYCoors[3] *= 3;
                dXCoors[4] *= 5;
                dYCoors[4] *= 7;
                dXCoors[5] *= 3;
                dYCoors[5] *= 7;
                dXCoors[6] *= 3;
                dYCoors[6] *= 3;

                break;
        }
        for(int i = 0; i < 7; i++)
        {
            XCoor[i] = X + dXCoors[i];
            YCoor[i] = Y + dYCoors[i];
        }
        Draw = new Color(Color.LIGHT_GRAY.getRed(), Color.LIGHT_GRAY.getGreen(), Color.LIGHT_GRAY.getBlue(), 50);
        Outline = new Color(255, 255, 255, 40);
    }
    public void tick(){
        Y += 4;
        for(int i = 0; i < 7; i++)
            YCoor[i] += 4;
        if(Y+(8*UNIT) >= DDR.ZONE){
            Draw = Color.YELLOW;
            Outline = Color.RED;
            if(Y+8*UNIT >= DigitalTimer.getHeight()){
                toDelete = true;
            }
        }
    }
    public void render(Graphics2D g2d){
        g2d.setColor(Draw);
        g2d.fillPolygon(XCoor,YCoor,numPoints);
        g2d.setColor(Outline);
        g2d.drawPolygon(XCoor,YCoor,numPoints);
    }
    public boolean getState(){
        return toDelete;
    }
}
