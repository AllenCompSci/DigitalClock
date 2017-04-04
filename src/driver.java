import generic.graphics.AudioPlayer;
import generic.graphics.CascdingWindow;
import generic.graphics.DigitalTimer;
import generic.graphics.MasterClock;


/**
 * Created by taylor hudson on 3/27/2017.
 */
public class driver {

    static Class driverClass = driver.class;
    public static void main(String [] args0){
        CascdingWindow handler = new CascdingWindow();
        AudioPlayer example = new AudioPlayer("Wilhelm.mp3");
        DigitalTimer time = new DigitalTimer();
        new Thread(example).start();
        new Thread(new MasterClock()).start();
        new Thread(handler).start();
        new Thread(time).start();
    }

}
