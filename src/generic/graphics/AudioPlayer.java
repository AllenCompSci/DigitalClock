package generic.graphics;

import javazoom.jlme.util.Player;

/* JavaZoom via
http://www.javazoom.net/javalayer/sourcesme.html
 */
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.jar.JarFile;


/**
 * Created by Taylor on 2/12/2017.
 * Short ReadMe the library file contains a jar file that has the necessary library for .mp3 files
 */
public class AudioPlayer implements Runnable{
    final String path = "";
    String pathToX = getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
    static String pathToMp3 = "";
    final File jarFile = new File(pathToX);
    JarFile jarfile;
    static ArrayList<Object> fi;
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

    private static void init(){
        try{
           File file = new File("resources/generic/graphics/" +input);

           if(file.exists()) {

               FileInputStream fis = new FileInputStream(file);
               BufferedInputStream bis = new BufferedInputStream(fis);
               player = new Player(bis);
           }
           else{

               DigitalTimer.theErrorMessage = "BIS";
               DigitalTimer.errorMessage = true;
               BufferedInputStream bis = new BufferedInputStream(AudioPlayer.class.getClass().getResourceAsStream(input));


               if(bis!= null) {
                   DigitalTimer.theErrorMessage = bis.toString();
                   player = new Player(bis);
               }
                else{
                   DigitalTimer.theErrorMessage = "bis null ";
               }
           }


        }
        catch (Exception e){
            DigitalTimer.bkcolor = Color.blue;
            DigitalTimer.errorMessage = true;
            DigitalTimer.theErrorMessage = e.toString();
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
        input =  spaceRemove(fileName);
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
