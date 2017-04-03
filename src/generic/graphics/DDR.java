package generic.graphics;

import java.awt.*;
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
    static int COLUMNSHIFT = UNIT * 19;
    static int ZONE = DigitalTimer.getHeight() - (int)(UNIT * 8 * 1.5);
    public DDR(){
     collection = new ArrayList<>();

    }
    public void add(){
        int RAND = (int)(Math.random() * 50);
        switch(RAND){
            case 0:
            case 12:
                collection.add(new Arrow(Direction.LEFT, COLUMN.RED_COL));
                break;
            case 1:
            case 25:
                collection.add(new Arrow(Direction.DOWN, COLUMN.YELLOW_COL));
                break;
            case 3:
            case 37:
                collection.add(new Arrow(Direction.RIGHT, COLUMN.GREEN_COL));
                break;
            case 6:
            case 49:
                collection.add(new Arrow(Direction.UP, COLUMN.BLUE_COL));
                break;
        }
        return;
    }
    public void tick(){
        for(int i = 0; i < collection.size(); i++){
            collection.get(i).tick();
            if(collection.get(i).getState()){
                collection.remove(i--);
            }
        }
    }
    public void render(Graphics2D g2d){
        for(Arrow  g : collection){
            g.render(g2d);
        }
    }
    public void sequence(Graphics2D g2d){
        if(collection.size() > 0)
            tick();
        add();
        if(collection.size() > 0)
            render(g2d);
    }
}

