package main;

import javafx.beans.InvalidationListener;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by apolol92 on 09.09.2015.
 * Class of each Song. It extends the thread class to use multithreading while playing.
 */
public class Song extends java.util.Observable implements Runnable{
    /**
     * Holds the audiodata
     */
    Player p1;
    /**
     * The title of the song.
     * The path to the current music-file.
     */
    String title, path;
    public Song(String path, String title) {
        this.path = path;
        this.title = title;
        try {
            FileInputStream in = new FileInputStream(path+"/"+title);
            this.p1 = new Player(in);
        }
        catch (Exception ex) {

        }
    }

    /**
     * This ist the thread of the program wich run the song.
     */
    @Override
    public void run() {
        try {
            p1.play();
            //Send signal to MusicPlayer
            setChanged();
            //Song is finished? (send true)
            notifyObservers(true);

        } catch (JavaLayerException e) {
            e.printStackTrace();
        }
    }

    /**
     * with this command you can stop the song.
     */
    public void stopSong() {
        this.p1.close();
        try {
            this.finalize();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    /**
     * This method restarts the song.
     */
    public void restart() {
        try {
            this.p1 = new Player(new FileInputStream(path+"/"+title));
            this.run();
        } catch (JavaLayerException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

}
