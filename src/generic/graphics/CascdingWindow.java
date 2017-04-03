package generic.graphics;

import javax.swing.*;

/**
 * Created by taylor hudson on 3/31/2017.
 */
public class CascdingWindow implements Runnable{
    public static boolean ALARM = false;

    public CascdingWindow(){

    }
    public void handleAlarmWindow(){
        String today = MasterClock.getMonth() + " " + MasterClock.getDay() + ", " + MasterClock.getYear();
        String[] AlarmSounds = {"Alert", "Beep", "Door Chime", "EAS", "Fire Alarm", "Fire Pager", "Horn", "House Fire Alarm", "Loud Buzzer", "Metronome", "Railroad",
                "Rooster Crow", "Rooster", "Ship Bell", "Temple Bell", "Tornado Siren", "Wilhelm"};
        String message = "Which Alarm Sound Would you like to play?";
        String input = (String) JOptionPane.showInputDialog(null, message, "EZ ALARM", JOptionPane.QUESTION_MESSAGE, null, AlarmSounds, AlarmSounds[3]);
        AudioPlayer.setInput(input + ".mp3");
        String[] Today = {"Yes", "No"};
        message = "Will you be setting an alarm for " + today + "?";
        input = (String) JOptionPane.showInputDialog(null, message, "EZ ALARM", JOptionPane.QUESTION_MESSAGE, null, Today, Today[0]);
        boolean isToday = true;
        if(input.equals("No")){
            isToday = false;
            // 0 - Month // 1 Day
            String [] Year = DigitalTimer.getYear();
            message = "Choose a Year : ";
            DigitalTimer.ALARMTIME[5] = (String) JOptionPane.showInputDialog(null, message, "EZ ALARM", JOptionPane.QUESTION_MESSAGE, null, Year, Year[0]);
            String [] Month;
            if(DigitalTimer.ALARMTIME[5].equals(MasterClock.getYear())){
                Month = DigitalTimer.getMonth();
            }
            else{
                Month = DigitalTimer.getMonths();
            }
            message = "Choose a Month : ";
            DigitalTimer.ALARMTIME[0] = (String) JOptionPane.showInputDialog(null, message, "EZ ALARM", JOptionPane.QUESTION_MESSAGE, null, Month, Month[0]);
            String [] Days = DigitalTimer.getDays(DigitalTimer.ALARMTIME[0]);
            message = "Choose a Day : ";
            DigitalTimer.ALARMTIME[1] =  (String) JOptionPane.showInputDialog(null, message, "EZ ALARM", JOptionPane.QUESTION_MESSAGE, null, Days, Days[0]);
            if(DigitalTimer.ALARMTIME[1].equals(MasterClock.getDay()) && DigitalTimer.ALARMTIME[0].equals(MasterClock.getMonth()) && DigitalTimer.ALARMTIME[5].equals(MasterClock.getYear())){
                isToday = true;
            }
        }
        else{
            DigitalTimer.ALARMTIME[5] = MasterClock.getYear();
            DigitalTimer.ALARMTIME[0] = MasterClock.getMonth();
            DigitalTimer.ALARMTIME[1] = MasterClock.getDay();
        }
        // Period
        String [] Period;
        DigitalTimer.ALARMTIME[4] = null;
        if(isToday){
            if("PM".equals(MasterClock.getAPM())){
                DigitalTimer.ALARMTIME[4] = "PM";
            }
        }
        if(DigitalTimer.ALARMTIME[4] == (null)){
            Period = new String[2];
            Period[0] = "AM";
            Period[1] = "PM";
            message = "Choose a Period : ";
            DigitalTimer.ALARMTIME[4] =  (String) JOptionPane.showInputDialog(null, message, "EZ ALARM", JOptionPane.QUESTION_MESSAGE, null, Period, Period[0]);
        }

        // Hour
        String [] Hour = DigitalTimer.getHours(isToday);
        DigitalTimer.ALARMTIME[2] =  (String) JOptionPane.showInputDialog(null, message, "EZ ALARM", JOptionPane.QUESTION_MESSAGE, null, Hour, Hour[0]);
        if(DigitalTimer.ALARMTIME[2].length() < 2){
            DigitalTimer.ALARMTIME[2] = "0" + DigitalTimer.ALARMTIME[2];
        }
        // Min
        String [] Min = DigitalTimer.getMins(isToday);
        DigitalTimer.ALARMTIME[3] = (String) JOptionPane.showInputDialog(null, message, "EZ ALARM", JOptionPane.QUESTION_MESSAGE, null, Min, Min[0]);
        if(DigitalTimer.ALARMTIME[3].length() < 2)
            DigitalTimer.ALARMTIME[3] = "0" + DigitalTimer.ALARMTIME[3];

    }

    @Override
    public void run() {
        while(DigitalTimer.isRunning){
            try {
                if(ALARM){
                    handleAlarmWindow();
                    ALARM = false;
                }
                Thread.sleep(25);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
