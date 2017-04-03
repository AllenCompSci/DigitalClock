package generic.graphics;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;

/**
 * Created by taylor hudson on 4/3/2017.
 */
public class Star {
    static int maxX = 19;
    static int maxY = 17;
    int X, Y, dx, dy;
    static int starUnit = 8;
    static Point A, B, C, D, E;
    LINE stardraw [];
    private boolean drawHelp[] = {false, true, true, true, true};
    private boolean toDelete;
    Color drawStar;
    public Star(int X, int Y, int dx, int dy){
        toDelete = false;
        drawStar = new Color((int)(Math.random()*256), (int)(Math.random()*256), (int)(Math.random()*256), (int)(Math.random()*256));
        this.X = X;
        this.Y = Y;
        this.dx = dx;
        this.dy = dy;
        A = new Point(0,7);
        B = new Point(19,7);
        C = new Point(1,17);
        D = new Point(10,0);
        E = new Point(16,17);
        stardraw = new LINE[5];
        for(int i = 0; i < 5; i++)
            stardraw[i] = new LINE(i);
    }
    public void render(Graphics2D g2){
        g2.setColor(drawStar);
        Path2D path = new Path2D.Double();
        for(int i = 0; i < 5; i++){
        path.append(new Line2D.Double(stardraw[i].getX1() + X, stardraw[i].getY1() + Y, stardraw[i].getX2() + X, stardraw[i].getY2() + Y), drawHelp[i]);
        }
        g2.draw(path);
        g2.fill(path);

    }
    public void tick(){
        X+= dx;
        Y+= dy;
        if(X < 0 || Y < 0 || X + (int)B.getX() > DigitalTimer.getWidth() || (int)E.getY() + Y > DigitalTimer.getHeight()){
            toDelete = true;
        }
    }
    public boolean getState(){
        return toDelete;
    }
}
class LINE{

    int x1, y1, x2, y2;
    public LINE(int index){
        switch(index){
            case 0:
                set(Star.A, Star.B);
                break;
            case 1:
                set(Star.B, Star.C);
                break;
            case 2:
                set(Star.C, Star.D);
                break;
            case 3:
                set(Star.D, Star.E);
                break;
            case 4:
                set(Star.E, Star.A);
                break;
        }
    }
    private void set1(Point star){
        x1 = (int)star.getX() * Star.starUnit;
        y1 = (int)star.getY() * Star.starUnit;
    }
    private void set2(Point star){
        x2 = (int)star.getX() * Star.starUnit;
        y2 = (int)star.getY() * Star.starUnit;
    }
    private void set(Point star1, Point star2){
        set1(star1);
        set2(star2);
    }
    public void draw(int X, int Y, Graphics2D g2){
        g2.drawLine(X + x1, Y + y1, X + x2, Y + y2);
    }
    public int getX1(){
        return x1;
    }
    public int getY1(){
        return y1;
    }
    public int getX2(){
        return x2;
    }
    public int getY2(){
        return y2;
    }
}
