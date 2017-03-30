package generic.graphics;

import javazoom.jlme.util.Player;

/* JavaZoom via
http://www.javazoom.net/javalayer/sourcesme.html
 */
import java.io.*;

/**
 * Created by Taylor on 2/12/2017.
 * Short ReadMe the library file contains a jar file that has the necessary library for .mp3 files
 */
public class AudioPlayer implements Runnable{
    private static Player player;
    private static String input;
    public static boolean isRunning;
    public static boolean play = false;
    public AudioPlayer(String fileName){
        isRunning = true;
        setInput(fileName);

    }
    /**
    Uses String input to setup an audioplayer.
     */
    private static InputStream getStream() throws IOException {
        InputStream bis = new BufferedInputStream(MasterClock.class.getResource("./"+input).openStream());
        return bis;
    }
    private static void init(){
        try{
           File file = new File(input);


            if(file.exists()) {
                FileInputStream fis = new FileInputStream(file);
                BufferedInputStream bis = new BufferedInputStream(fis);
                player = new Player(bis);
            }
            else{


            }


        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public void playOnce(){
        try {
            player.play();
            init();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void setInput(String fileName){
        input = "resource/" + spaceRemove(fileName);
        //System.out.println(input);

        init();
    }
    private static String spaceRemove(String fileName){
        //System.out.println(fileName);
        while(fileName.indexOf(' ')>=0){
            fileName = fileName.replace(' ', '-');
            //System.out.println(fileName);
        }
        //System.out.println("done");
        return fileName;
    }
    @Override
    public void run() {
        while(isRunning){

            try{
                if(play) {
                    playOnce();
                    play = false;
                }
                Thread.sleep(10);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
