package generic.graphics;

import generic.graphics.Graphics;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by taylor hudson on 3/27/2017.
 */
public class DigitalTimer extends Graphics{
    public static boolean isRunning;
    private int ss, hh, mm, dd, yyyy;
    private int display[];
    private String info[];
    private Complex val[];
    private int CircleX[];
    private int CircleY1;
    private int CircleY2;
    int XAlpha[], YAlpha[];
    private double unit;
    private Alphabet myalpha;
    private static final int transColorVal = 30;
    static Color clockColor;
    static Color transparent;
    int testLetter;
    final int POSX = 55;
    final int POSY = 55;
    public DigitalTimer() {
        super("Digital Timer", 1100, 600);
        randColor();
        testLetter = 0; // TEST VALUE UNUSED
        frame.setVisible(true);
        init();

}
public void randColor(){
    switch((int)(Math.random()*3)) {
        case 0:
        clockColor = Color.green;
        transparent = new Color(clockColor.getRed(), clockColor.getGreen() - 30, clockColor.getBlue(), 50);
        return;
        case 1:
            clockColor = new Color(55,251,218);
            transparent = new Color(clockColor.getRed(), clockColor.getGreen(), clockColor.getBlue()- 30, 40);
            return;

    }
    clockColor = Color.red;
    transparent = new Color(clockColor.getRed()-30, clockColor.getGreen(), clockColor.getBlue(), 50);

}
public void updateTime(){
    ss = Integer.valueOf(MasterClock.getSec());
    mm = Integer.valueOf(MasterClock.getMin());
    hh = Integer.valueOf(MasterClock.getHour());
    dd = Integer.valueOf(MasterClock.getDay());
    yyyy = Integer.valueOf(MasterClock.getYear());
    display[0] = hh/10;
    display[1] = hh%10;
    display[2] = mm/10;
    display[3] = mm%10;
    display[4] = ss/10;
    display[5] = ss%10;
    display[6] = dd/10;
    display[7] = dd%10;

    display[8] = yyyy / 1000;
    display[9] = (yyyy/100)%10;
    display[10] = (yyyy / 10) % 10;
    display[11] = yyyy %10;
    info[0] = monthShift(MasterClock.getMonth());
    info[1] = dayShift(MasterClock.getDayoftheWeek());
    info[2] = MasterClock.getAPM();
    info[3] = MasterClock.getTIMEZONE();
}

private void init(){
    display = new int[12];
    /**
     * 0 - MONTH 1 - DAY 2 - AM/PM 3 - TIMEZONE
     */
    info = new String[4];
    XAlpha = new int[9];
    YAlpha = new int[9];
    val = new Complex[12];
    int x = POSX;
    CircleX = new int[2];
    val[0] = new Complex(x,55);
    unit = val[0].getUnit();
    CircleY1 = (int)(POSY + (unit * 1.5));
    CircleY2 = (int)(POSY + (unit * 4.5));
    x += 4*unit;
    x +=2;
    val[1] = new Complex(x, POSY);
    CircleX[0] = x + (int)(5* unit);
    x += 7 * unit;
    val[2] = new Complex(x,POSY);
    x += 4*unit;
    x +=2;
    val[3] = new Complex(x,POSY);
    CircleX[1] = x + (int)(5* unit);
    x += 7 * unit;
    val[4] = new Complex(x,POSY);
    x += 4*unit;
    x +=2;
    val[5] = new Complex(x,POSY);
    int Row2X = POSX;
    int Row2Y = POSY + (int)(unit * 9.5);

    for(int j = 0; j < 3; j++){
        Row2X += (int)(unit * 5.5);
    }
    Row2X += (int)(unit * 2.5);

    val[6] = new Complex(Row2X,Row2Y);
    Row2X += (int)(4*unit);

    val[7] = new Complex(Row2X, Row2Y);
    Row2X += 5.5 * unit;

    for(int i= 0; i < 4; i++){
        val[8+i] = new Complex(Row2X,Row2Y);
        Row2X += (int)(4*unit);
    }
    myalpha = new Alphabet((int)unit);
    isRunning = true;

    int index = 0;

    Row2X = POSX;
    Row2Y = POSY + (int)(unit * 9);

    for(int i = 0; i  < 2; i++){
        for(int j = 0; j < 3; j++){
            XAlpha[index] = Row2X;
            YAlpha[index] = Row2Y;
            Row2X += (int)(unit * 5.5);

            index ++;
        }
        if(i == 0)
        {
            Row2X = val[0].getX() + (int)(unit * 4.5);
            Row2Y += (unit * 9.5);
        }
    }
    Row2X += (int)(unit * 3);
    Date t = new Date();
    for(int j = 0; j < (int)MasterClock.timezone.format(t).length(); j++){
        XAlpha[index] = Row2X;
        YAlpha[index] = Row2Y;
        Row2X += (int)(unit * 5.5);

        index++;
    }
}

    @Override
    public void run(){
       while(isRunning()) {
           try {
               updateTime();
               draw();
               Thread.sleep(25);
           } catch (Exception e) {
               e.printStackTrace();
           }
       }
       isRunning = false;

   }

   public void draw(){
       imgBuffer = frame.createImage(frame.getWidth(), frame.getHeight());
       startDraw();
       drawBackground(art);
       drawSeperator(art);
       drawNumbers(art);
       drawAlpha(art);


       /*
       This goes through all the letters of the alphabet
       testALPHA(art);
        */
       endDraw();
   }

    /**
     * 0 - MONTH 1 - DAY 2 - AM/PM 3 - TIMEZONE
     */
    private String dayShift(String day){
        switch(day){
            case "Sunday":
                return "SUN";
            case "Monday":
                return "MON";
            case "Tuesday":
                return "TUE";
            case "Wednesday":
                return "WED";
            case "Thursday":
                return "THU";
            case "Friday":
                return "FRI";
        }
        return "SAT";
    }
    private String monthShift(String month) {
        switch(month){
            case "January":
                return "JAN";
            case "February":
                return "FEB";
            case "March":
                return "MAR";
            case "April":
                return "APR";
            case "May":
                return "MAY";
            case "June":
                return "JUN";
            case "July":
                return "JUL";
            case "August":
                return "AUG";
            case "September":
                return "SEP";
            case "October":
                return "OCT";
            case "November":
                return "NOV";
        }
        return "DEC";
    }
    private void drawSeperator(Graphics2D g2d){
        g2d.setColor(clockColor);
        g2d.fillOval(CircleX[0], CircleY1, (int)unit, (int)unit);
        g2d.fillOval(CircleX[0], CircleY2, (int)unit, (int)unit);
        g2d.fillOval(CircleX[1], CircleY1, (int)unit, (int)unit);
        g2d.fillOval(CircleX[1], CircleY2, (int)unit, (int)unit);
    }
    private void drawNumbers(Graphics2D g2d){
        for(int i = 0; i < 12; i++)
            val[i].draw(g2d, display[i]);

    }
    private void drawBackground(Graphics2D g2d){
        g2d.setColor(Color.black);
        g2d.fillRect(0,0,frame.getWidth(), frame.getHeight());
    }
    private void drawAlpha(Graphics2D g2d) {
        /// AM or PM
        for(int i = 0; i < info[2].length(); i++)
            myalpha.draw(g2d,info[2].toCharArray()[i], val[5].getX() + (int) (unit*(6+(i*6))), val[5].getY());

        int number = 0;
        /// 0 - MONTH AND 1 - DAY
        for(int i = 0; i  < 2; i++){
            for(int j = 0; j < info[i].length(); j++){
                myalpha.draw(g2d,info[i].toCharArray()[j], XAlpha[number], YAlpha[number]);
                number ++;
            }
        }
        /// TIMEZONE
        for(int j = 0; j < info[3].length(); j++){
            myalpha.draw(g2d,info[3].toCharArray()[j], XAlpha[number], YAlpha[number]);
            number ++;
        }
    }
    private void testALPHA(Graphics2D art){
        myalpha.draw(art,(char)(65+((testLetter++/15)%26)), 55 + (int)(unit * 19),55+(int)(unit*9)) ;
    }
}
