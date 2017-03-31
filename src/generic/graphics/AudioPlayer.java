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
           File file = new File("resource/audio/" +input);
            //System.out.println(ResourceLoader.class.getResourceAsStream(input).toString());
           if(file.exists()) {

               FileInputStream fis = new FileInputStream(file);
               BufferedInputStream bis = new BufferedInputStream(fis);
               player = new Player(bis);
           }
           else{
               /*
               file = new File("resource/" +input);
               DigitalTimer.bkcolor = Color.gray;
               Thread.sleep(900);
               FileInputStream fis = new FileInputStream(file);
               DigitalTimer.bkcolor = Color.orange;
               Thread.sleep(900);
               */

               //JOptionPane.showMessageDialog(null,ResourceLoader.class.getResourceAsStream("src/resource/" + input).toString(), JOptionPane.MESSAGE_PROPERTY, 3,null);
               //fis);

               //InputStream root = driver.class.getResourceAsStream("/src/generic/graphics/" + input);
              // DigitalTimer.bkcolor = Color.pink;
               //Thread.sleep(2000);
               BufferedInputStream bis = new BufferedInputStream(AudioPlayer.class.getResourceAsStream("./resource/" + input));
               //DigitalTimer.bkcolor = Color.white;
               //Thread.sleep(2000);
               //DigitalTimer.errorMessage = true;
               DigitalTimer.theErrorMessage = AudioPlayer.class.getResourceAsStream("./resource/" + input).toString();
               if(bis!= null)
                    player = new Player(bis);


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
    /*
    public static URL getResource(String resource){

        URL url ;

        //Try with the Thread Context Loader.
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        if(classLoader != null){
            url = classLoader.getResource(resource);
            if(url != null){
                return url;
            }
        }

        //Let's now try with the classloader that loaded this class.
        classLoader = Loader.class.getClassLoader();
        if(classLoader != null){
            url = classLoader.getResource(resource);
            if(url != null){
                return url;
            }
        }

        //Last ditch attempt. Get the resource from the classpath.
        return ClassLoader.getSystemResource(resource);
    }*/
}
