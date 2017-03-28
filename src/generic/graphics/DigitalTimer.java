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
private double unit;
private Alphabet myalpha;
    static Color clockColor;
    static Color transparent;
    int testLetter;
public DigitalTimer() {
    super("Digital Timer", 1100, 600);

    testLetter = 0;
     frame.setVisible(true);
    randColor();
  //  imgBuffer = frame.createImage(frame.getWidth(), frame.getHeight());
    display = new int[12];
    /**
    0 - DAY 1 - MONTH 2 - AM/PM 3 - TIMEZONE
     */
    info = new String[4];

    val = new Complex[12];
    int x = 55;
    CircleX = new int[2];
    val[0] = new Complex(x,55);
    unit = val[0].getUnit();
    CircleY1 = (int)(55 + (unit * 2));
    CircleY2 = (int)(55 + (unit * 5));
    x += 4*unit;
    x +=2;
    val[1] = new Complex(x, 55);
    CircleX[0] = x + (int)(5* unit);
    x += 7 * unit;
    val[2] = new Complex(x,55);
    x += 4*unit;
    x +=2;
    val[3] = new Complex(x,55);
    CircleX[1] = x + (int)(5* unit);
    x += 7 * unit;
    val[4] = new Complex(x,55);
    x += 4*unit;
    x +=2;
    val[5] = new Complex(x,55);
    int Row2X = val[0].getX();
    int Row2Y = val[0].getY() + (int)(unit * 9.5);
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
}
public void randColor(){
    switch((int)(Math.random()*3)) {
        case 0:
        clockColor = Color.green;
        transparent = new Color(clockColor.getRed(), clockColor.getGreen() - 30, clockColor.getBlue(), 50);
        return;
        case 1:
            clockColor = new Color(55,251,218);
            transparent = new Color(clockColor.getRed(), clockColor.getGreen(), clockColor.getBlue()- 30, 50);
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
    info[0] = dayShift(MasterClock.getDayoftheWeek());
    info[1] = monthShift(MasterClock.getMonth());
    info[2] = MasterClock.getAPM();
    info[3] = MasterClock.getTIMEZONE();
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
       art.setColor(Color.black);
       art.fillRect(0,0,frame.getWidth(), frame.getHeight());
       for(int i = 0; i < 12; i++)
           val[i].draw(art, display[i]);
       art.setColor(clockColor);
       art.fillOval(CircleX[0], CircleY1, (int)unit, (int)unit);
       art.fillOval(CircleX[0], CircleY2, (int)unit, (int)unit);
       art.fillOval(CircleX[1], CircleY1, (int)unit, (int)unit);
       art.fillOval(CircleX[1], CircleY2, (int)unit, (int)unit);

       for(int i = 0; i < (int)info[2].length(); i++)
        myalpha.draw(art,info[2].toCharArray()[i], val[5].getX() + (int) (unit*(6+(i*6))), val[5].getY());

       int Row2X = val[0].getX();
       int Row2Y = val[0].getY() + (int)(unit * 9);
       for(int i = 0; i  < 2; i++){
           for(int j = 0; j < (int)info[i].length(); j++){
               myalpha.draw(art,info[i].toCharArray()[j], Row2X, Row2Y);
               Row2X += (int)(unit * 5.5);
           }
           if(i == 0)
           {
               Row2X = val[0].getX() + (int)(unit * 4.5);
               Row2Y += (unit * 9.5);
           }
       }
       Row2X += (int)(unit * 3);
       for(int j = 0; j < (int)info[3].length(); j++){
           myalpha.draw(art,info[3].toCharArray()[j], Row2X, Row2Y);
           Row2X += (int)(unit * 5.5);
       }


       /*
       This goes through all the letters of the alphabet
       myalpha.draw(art,(char)(65+((testLetter++/15)%26)), 55 + (int)(unit * 19),55+(int)(unit*9)) ;

        */
       endDraw();
   }
    /**
     0 - DAY 1 - MONTH 2 - AM/PM 3 - TIMEZONE
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

}
