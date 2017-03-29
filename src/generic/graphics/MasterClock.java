package generic.graphics;

import generic.graphics.DigitalTimer;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by taylor hudson on 3/27/2017.
 */
public class MasterClock implements Runnable {
    public enum CLOCKSTATE{CLOCK, TIMER, STOPWATCH, ALARM};
    private static Date date = null;
    private static SimpleDateFormat dow = new SimpleDateFormat("EEEE");
    private static SimpleDateFormat month = new SimpleDateFormat("MMMM");
    private static SimpleDateFormat day = new SimpleDateFormat("d");
    private static SimpleDateFormat year = new SimpleDateFormat("yyyy");
    private static SimpleDateFormat hr = new SimpleDateFormat("hh");
    private static SimpleDateFormat min = new SimpleDateFormat("mm");
    private static SimpleDateFormat sec = new SimpleDateFormat("ss");
    private static SimpleDateFormat period = new SimpleDateFormat("a");
    public static SimpleDateFormat timezone = new SimpleDateFormat("zzz");
    public MasterClock() {
        update();

        //// EXAMPLE
        // SimpleDateFormat mutate = new SimpleDateFormat("EEEE, MMMM d, yyyy 'at' hh:mm:ss a zzz");

        update();

        System.out.print(getDayoftheWeek() + ", ");
        System.out.print(getMonth() + " ");
        System.out.print(getDay() + ", ");
        System.out.print(getYear()+ " at ");
        update();
        System.out.print(getHour() + ":");
        System.out.print(getMin() + ":");
        System.out.print(getSec() + " ");
        System.out.print(getAPM() + " ");
        System.out.println(getTIMEZONE());

    }
    private void update(){ date = new Date();

    }
    public static String getDayoftheWeek(){
        return dow.format(date);
    }
    public static String getMonth(){
        return month.format(date);
    }
    public static String getDay(){
        return day.format(date);
    }
    public static String getYear(){
        return year.format(date);
    }
    public static String getHour(){
        return hr.format(date);
    }
    public static String getMin(){
        return min.format(date);
    }
    public static String getSec(){
        return sec.format(date);
    }
    public static String getAPM(){
        return period.format(date);
    }
    public static String getTIMEZONE(){
        return timezone.format(date);
    }

    @Override
    public void run() {
        while(DigitalTimer.isRunning) {

            try{
                update();
                Thread.sleep(12);
            }
            catch (Exception e){
                e.printStackTrace();
            }

        }
    }
}
