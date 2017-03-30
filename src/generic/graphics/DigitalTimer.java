package generic.graphics;

import generic.graphics.Graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static generic.graphics.MasterClock.CLOCKSTATE.*;

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
    private String ALARMTIME[];
    private Alphabet myalpha;
    private static final int transColorVal = 30;
    static Color clockColor;
    static Color transparent;
    int testLetter;
    final int POSX = 55;
    final int POSY = 55;
    static MasterClock.CLOCKSTATE window;
    static boolean ALARM = false;
    static boolean switchState = false;
    static boolean alarm = false;
    private long stopwatchTime[];
    private long clockupdate[];
    private String today;
    public DigitalTimer() {
        super("Digital Timer", 1100, 600);
        randColor();
        window = CLOCK;
        stopwatchTime = new long[4];
        stopwatchTime[0] = 0;
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
    today = MasterClock.getMonth() + " " + MasterClock.getDay() + ", " + MasterClock.getYear();
    if(switchState){
        stopwatchTime[0] = 0;
        stopwatchTime[1] = 0;
        stopwatchTime[2] = stopwatchTime[1] ;
        if(window == TIMER){
            stopwatchTime[1] += getTIMER();
        }
        stopwatchTime[1] += System.currentTimeMillis();
        if(window == ALARMS){
            setAlarm();
            ALARM = false;
        }
        switchState = false;
    }
    if(alarm && !ALARM){
        checkAlarm();
    }
    else{
        alarm = false;
        if(ALARM){
            randColor();
        }
    }
    switch (window) {
        case ALARMS:
        case CLOCK:
            ss = Integer.valueOf(MasterClock.getSec());
            mm = Integer.valueOf(MasterClock.getMin());
            hh = Integer.valueOf(MasterClock.getHour());
            break;
        case TIMER:
            if(stopwatchTime[1] < stopwatchTime[2]){
                window = CLOCK;
                break;
            }
        case STOPWATCH:
            stopwatchTime[2] = System.currentTimeMillis();
            stopwatchTime[0] = Math.abs(stopwatchTime[2]-stopwatchTime[1]);
            ss = (int)(stopwatchTime[0]/1000);
            mm = (ss/60)%60;
            hh = (ss/3600);
            ss %= 60;
            break;


    }
    display[0] = hh / 10;
    display[1] = hh % 10;
    display[2] = mm / 10;
    display[3] = mm % 10;
    display[4] = ss / 10;
    display[5] = ss % 10;

    display[6] = dd / 10;
    display[7] = dd % 10;
    display[8] = yyyy / 1000;
    display[9] = (yyyy/100)%10;
    display[10] = (yyyy / 10) % 10;
    display[11] = yyyy %10;
    info[0] = monthShift(MasterClock.getMonth());
    info[1] = dayShift(MasterClock.getDayoftheWeek());
    info[2] = MasterClock.getAPM();
    info[3] = MasterClock.getTIMEZONE();
}

    private long getTIMER() {
        String [] resp = new String[3];
        resp[0] =
                JOptionPane.showInputDialog ( "How many Hours? ");

        resp[1] =
                JOptionPane.showInputDialog ( "How many Minutes? " );

        resp[2] =
                JOptionPane.showInputDialog("How many Seconds? ");
        long time = 0;
        for(int i = 0; i < 3; i++) {
            time += Integer.valueOf(resp[i]) * clockupdate[i];
        }
        return time;
    }

    private void setAlarm(){
        if(switchState) {
            String[] Today = {"Yes", "No"};
            String message = "Will you be setting an alarm for " + today + "?";
            String input = (String) JOptionPane.showInputDialog(null, message, "EZ ALARM", JOptionPane.QUESTION_MESSAGE, null, Today, Today[0]);
            boolean isToday = true;
            if(input.equals("No")){
                isToday = false;
                // 0 - Month // 1 Day
                String [] Year = getYear();
                message = "Choose a Year : ";
                ALARMTIME[5] = (String) JOptionPane.showInputDialog(null, message, "EZ ALARM", JOptionPane.QUESTION_MESSAGE, null, Year, Year[0]);
                String [] Month;
                if(ALARMTIME[5].equals(MasterClock.getYear())){
                    Month = getMonth();
                }
                else{
                    Month = getMonths();
                }
                message = "Choose a Month : ";
                ALARMTIME[0] = (String) JOptionPane.showInputDialog(null, message, "EZ ALARM", JOptionPane.QUESTION_MESSAGE, null, Month, Month[0]);
                String [] Days = getDays(ALARMTIME[0]);
                message = "Choose a Day : ";
                ALARMTIME[1] =  (String) JOptionPane.showInputDialog(null, message, "EZ ALARM", JOptionPane.QUESTION_MESSAGE, null, Month, Days[0]);
                if(ALARMTIME[1].equals(MasterClock.getDay()) && ALARMTIME[0].equals(MasterClock.getMonth()) && ALARMTIME[5].equals(MasterClock.getYear())){
                    isToday = true;
                }
            }
            else{
                ALARMTIME[5] = MasterClock.getYear();
                ALARMTIME[0] = MasterClock.getMonth();
                ALARMTIME[1] = MasterClock.getDay();
            }
            // Period
            String [] Period;
            ALARMTIME[4] = null;
            if(isToday){
                if("PM".equals(MasterClock.getAPM())){
                    ALARMTIME[4] = "PM";
                }
            }
            if(ALARMTIME[4] == (null)){
                Period = new String[2];
                Period[0] = "AM";
                Period[1] = "PM";
                message = "Choose a Period : ";
                ALARMTIME[4] =  (String) JOptionPane.showInputDialog(null, message, "EZ ALARM", JOptionPane.QUESTION_MESSAGE, null, Period, Period[0]);
            }

            // Hour
            String [] Hour = getHours(isToday);
            ALARMTIME[2] =  (String) JOptionPane.showInputDialog(null, message, "EZ ALARM", JOptionPane.QUESTION_MESSAGE, null, Hour, Hour[0]);
            if(ALARMTIME[2].length() < 2){
                ALARMTIME[2] = "0" + ALARMTIME[2];
            }
            // Min
            String [] Min = getMins(isToday);
            ALARMTIME[3] = (String) JOptionPane.showInputDialog(null, message, "EZ ALARM", JOptionPane.QUESTION_MESSAGE, null, Min, Min[0]);
            if(ALARMTIME[3].length() < 2)
            ALARMTIME[3] = "0" + ALARMTIME[3];
        }

    }
    private void checkAlarm() {
        if(ALARMTIME[3].equals(MasterClock.getMin()) &&  ALARMTIME[2].equals(MasterClock.getHour()) &&
                ALARMTIME[0].equals(MasterClock.getMonth()) && ALARMTIME[1].equals(MasterClock.getDay()) &&
                ALARMTIME[4].equals(MasterClock.getAPM())){
            ALARM = true;
            System.out.println("ALARM GOES OFF");
        }
    }



    private void init(){
        // 0 - Month // 1 - Day // 2 - Hour // 3 - Min // 4 - Period // 5 - Year
        ALARMTIME = new String[6];
        clockupdate = new long[3];
        clockupdate[2] = 1000;
        for(int i = 1; i >= 0; i--){
            clockupdate[i] = clockupdate[i+1] * 60;
        }
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
       switch(window){
           case ALARMS:
           case CLOCK:
               drawSeperator(art);
               drawNumbers(art);
               drawAlpha(art);
               break;
           case TIMER:
           case STOPWATCH:
               drawSeperator(art);
               drawNumbers(art);
               break;
       }

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
    private String[] getHours(boolean isToday) {
        String [] hrs;
        int end = 12;
        int start = 12;
        if(isToday && ALARMTIME[4].equals(MasterClock.getAPM())){
            start = Integer.valueOf(MasterClock.getHour());

        }
        if(start == 12){
            start = 0;
        }
        hrs = new String[end-start];

        for( int i = 0; start+i < end; i ++){
            if(i+start == 0) {
                hrs[0] = "12";
            }
            else{
                hrs[i] = String.valueOf(start+i);
            }
        }
        return hrs;
    }
// 0 - Month // 1 - Day // 2 - Hour // 3 - Min // 4 - Period // 5 - Year

    private String[] getMins(boolean isToday) {

        String [] mins;
        int end = 60;
        int start = 0;
        if(isToday && ALARMTIME[4].equals(MasterClock.getAPM()) && ALARMTIME[2].equals(MasterClock.getHour())){
            start = Integer.valueOf(MasterClock.getMin());
        }
        mins = new String[end-start];

        for( int i = 0; start+i < end; i ++){
                mins[i] = String.valueOf(start+i);
        }
        return mins;

    }
    private String [] getYear(){
        int YYYY = Integer.valueOf(MasterClock.getYear());
        String[] y = new String[5];
        for(int i = 0; i < 5; i++){
            y[i] = String.valueOf(YYYY++);
        }
        return y;
    }
    private String[] getDays(String month){
        int days = 28;
        switch(month) {
            case "January":
            case "March":
            case "May":
            case "July":
            case "August":
            case "October":
            case"December":
                days = 31;
                break;
            case "April":
            case "June":
            case "September":
            case "November":
                days = 30;
        }
        int start = 1;
        if(month.equals("February")){
            int y = Integer.valueOf(ALARMTIME[5]);
            if(y % 4 == 0 && (y % 100 != 0 || y % 400 == 0)){
                days = 29;
            }
            else
                days = 28;
        }
        if(month.equals(MasterClock.getMonth()) && ALARMTIME[5].equals(MasterClock.getYear())){
            start = Integer.valueOf(MasterClock.getDay());
        }
        String [] ds = new String [days-start];
        for(int i = 0; i < ds.length; i++){
            ds[i] = String.valueOf(start+i);
        }
        return ds;
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
    private String []getMonth(){
        String [] Month = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        int CurrentMonth = monthNum(MasterClock.getMonth());
        String [] fixed = new String[12 - CurrentMonth - 1];
        for(int i = 0; i < fixed.length; i++){
            fixed[i] = Month[CurrentMonth-1+i];
        }
        return fixed;
    }
    private String[] getMonths(){
        String [] Month = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        return Month;
    }
    private int monthNum(String month) {
        switch(month){
            case "January":
                return 1;
            case "February":
                return 2;
            case "March":
                return 3;
            case "April":
                return 4;
            case "May":
                return 5;
            case "June":
                return 6;
            case "July":
                return 7;
            case "August":
                return 8;
            case "September":
                return 9;
            case "October":
                return 10;
            case "November":
                return 11;
        }
        return 12;
    }
    private void drawSeperator(Graphics2D g2d){
        g2d.setColor(clockColor);
        g2d.fillOval(CircleX[0], CircleY1, (int)unit, (int)unit);
        g2d.fillOval(CircleX[0], CircleY2, (int)unit, (int)unit);
        g2d.fillOval(CircleX[1], CircleY1, (int)unit, (int)unit);
        g2d.fillOval(CircleX[1], CircleY2, (int)unit, (int)unit);
    }
    private void drawNumbers(Graphics2D g2d){
        int size = 12;
        if(window != CLOCK && window != ALARMS)
            size = 6;
        for(int i = 0; i < size; i++)
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
