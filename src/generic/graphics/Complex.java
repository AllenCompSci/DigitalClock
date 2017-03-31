package generic.graphics;

import java.awt.*;

/**
 * Created by taylor hudson on 3/28/2017.
 */
public class Complex {
    private float unit = 20; // 2 pixels needs to always be even
    private Point Starting;
    private Polygon set[];
    private boolean num [][];

    public Complex(double x, double y){
        Starting = new Point();
        Starting.setLocation(x,y);
        defineRegion();

    }
    public void defineRegion(){
        set = new Polygon[7];
        num = new boolean[10][];
        for(int i = 0; i < 10; i++){
            num[i] = new boolean[7];
            for(int j = 0; j < 7; j++){
                num[i][j] = true;
            }
        }

        num[0][3] = false;
        num[1][0] = num[1][1] = num[1][3] = num[1][4] = num[1][6] = false;
        num[2][1] = num[2][5] = false;
        num[3][1] = num[3][4] = false;
        num[4][0] = num[4][4] = num[4][6] = false;
        num[5][2] = num[5][4] = false;
        num[6][2] = false;
        num[7][1] = num[7][3] = num[7][4] = num[7][6] = false;
        // none for 8
        num[9][4] = false;
        for(int i = 0; i < 7 ; i++) {
            set[i] = new Polygon();
        }
        double Xs = Starting.getX();
        double Ys = Starting.getY();

        set[0].addPoint((int)(Xs + (.5) * unit), (int)(Ys + (.5) * unit)); // 0
        set[0].addPoint((int)(Xs + unit), (int)(Ys)); // 1
        set[0].addPoint((int)(Xs + 3 * unit), (int)(Ys)); // 2
        set[0].addPoint((int)(Xs + 3.5 * unit), (int)(Ys + .5 * unit)); // 3
        set[0].addPoint((int)(Xs + 3 * unit), (int)(Ys + unit)); // 4
        set[0].addPoint((int)(Xs + unit), (int)(Ys + unit)); // 5

        set[1].addPoint((int)(Xs + (.5) * unit), (int)(Ys + (.5) * unit)); // 0
        set[1].addPoint((int)(Xs + unit), (int)(Ys + unit)); // 1
        set[1].addPoint((int)(Xs + unit), (int)(Ys + 3 * unit)); // 2
        set[1].addPoint((int)(Xs + .5 * unit), (int)(Ys + 3.5 * unit)); // 3
        set[1].addPoint((int)(Xs), (int)(Ys + 3 * unit)); // 4
        set[1].addPoint((int)(Xs), (int)(Ys + unit)); // 5

        set[2].addPoint((int)(Xs + (3.5) * unit), (int)(Ys + (.5) * unit)); // 0
        set[2].addPoint((int)(Xs + 4* unit), (int)(Ys + unit)); // 1
        set[2].addPoint((int)(Xs + 4* unit), (int)(Ys + 3 * unit)); // 2
        set[2].addPoint((int)(Xs + 3.5 * unit), (int)(Ys + 3.5 * unit)); // 3
        set[2].addPoint((int)(Xs + 3 * unit), (int)(Ys + 3 * unit)); // 4
        set[2].addPoint((int)(Xs + 3 * unit), (int)(Ys + unit)); // 5

        Ys += 3 * unit;
        set[3].addPoint((int)(Xs + (.5) * unit), (int)(Ys + (.5) * unit)); // 0
        set[3].addPoint((int)(Xs + unit), (int)(Ys)); // 1
        set[3].addPoint((int)(Xs + 3 * unit), (int)(Ys)); // 2
        set[3].addPoint((int)(Xs + 3.5 * unit), (int)(Ys + .5 * unit)); // 3
        set[3].addPoint((int)(Xs + 3 * unit), (int)(Ys + unit)); // 4
        set[3].addPoint((int)(Xs + unit), (int)(Ys + unit)); // 5

        set[4].addPoint((int)(Xs + (.5) * unit), (int)(Ys + (.5) * unit)); // 0
        set[4].addPoint((int)(Xs + unit), (int)(Ys + unit)); // 1
        set[4].addPoint((int)(Xs + unit), (int)(Ys + 3 * unit)); // 2
        set[4].addPoint((int)(Xs + .5 * unit), (int)(Ys + 3.5 * unit)); // 3
        set[4].addPoint((int)(Xs), (int)(Ys + 3 * unit)); // 4
        set[4].addPoint((int)(Xs), (int)(Ys + unit)); // 5

        set[5].addPoint((int)(Xs + (3.5) * unit), (int)(Ys + (.5) * unit)); // 0
        set[5].addPoint((int)(Xs + 4* unit), (int)(Ys + unit)); // 1
        set[5].addPoint((int)(Xs + 4* unit), (int)(Ys + 3 * unit)); // 2
        set[5].addPoint((int)(Xs + 3.5 * unit), (int)(Ys + 3.5 * unit)); // 3
        set[5].addPoint((int)(Xs + 3 * unit), (int)(Ys + 3 * unit)); // 4
        set[5].addPoint((int)(Xs + 3 * unit), (int)(Ys + unit)); // 5

        Ys += 3 * unit;
        set[6].addPoint((int)(Xs + (.5) * unit), (int)(Ys + (.5) * unit)); // 0
        set[6].addPoint((int)(Xs + unit), (int)(Ys)); // 1
        set[6].addPoint((int)(Xs + 3 * unit), (int)(Ys)); // 2
        set[6].addPoint((int)(Xs + 3.5 * unit), (int)(Ys + .5 * unit)); // 3
        set[6].addPoint((int)(Xs + 3 * unit), (int)(Ys + unit)); // 4
        set[6].addPoint((int)(Xs + unit), (int)(Ys + unit)); // 5


    }
    public void draw(Graphics2D g2, int val){


        for(int i = 0; i < 7; i++){
            if(num[val][i]){
                g2.setColor(DigitalTimer.clockColor);
                g2.fillPolygon(set[i]);
            }
            else{
                g2.setColor(DigitalTimer.transparent);
                g2.fillPolygon(set[i]);
            }
            g2.setColor(DigitalTimer.bkcolor);
            g2.drawPolygon(set[i]);
        }
    }
    public float getUnit(){
        return unit;
    }
    public int getY(){
        return (int)Starting.getY();
    }
    public int getX(){
        return (int)(Starting.getX());
    }
}
