import generic.graphics.AudioPlayer;
import generic.graphics.DigitalTimer;
import generic.graphics.MasterClock;

/**
 * Created by taylor hudson on 3/27/2017.
 */
public class driver {

    public static void main(String [] args0){
        DigitalTimer time = new DigitalTimer();
        AudioPlayer example = new AudioPlayer("0477.mp3");
        new Thread(example).start();
        new Thread(new MasterClock()).start();
        new Thread(time).start();
    }

}
