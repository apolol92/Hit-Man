package main;

import java.io.File;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by apolol92 on 10.09.2015.
 * This class is the music player.
 */
public class MusicPlayer implements Observer {
    /**
     * This String containts the path to the music
     */
    String musicFolder;
    /**
     * This thread is used for playing songs via multithreading
     */
    Thread currentThread;
    /**
     * This song contains the music
     */
    Song currentSong;
    /**
     * At which position is the current song in the songList
     */
    int songPosition;
    /**
     * This list holds all song titles
     */
    ArrayList<String> songList;

    /**
     * Initialize a music player.
     * This constructor needed a String, which show the MusicPlayer the path to the music.
     * @param musicFolder
     */
    public MusicPlayer(String musicFolder) {
        this.songPosition = 0;
        this.musicFolder = musicFolder;
        this.songList = new ArrayList<>();
        this.updateSongList();
    }

    /**
     * This update method is called, after a Observable sent a notification
     * @param o observable
     * @param arg arguments
     */
    @Override
    public void update(Observable o, Object arg) {
        this.nextSong();
    }

    /**
     * Use this method to play the next song in the list..
     */
    public void nextSong() {
        this.currentSong.deleteObservers();
        this.currentSong.stopSong();
        do {
            try {
                this.currentThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }while(this.currentThread.isAlive());
        this.songPosition++;
        if(this.songPosition==this.songList.size()) {
            this.songPosition=0;
        }
        this.playCurrentySong();
    }

    /**
     * Use this method to play the previous song in the list..
     */
    public void prevSong() {
        this.currentSong.deleteObservers();
        this.currentSong.stopSong();
        do {
            try {
                this.currentThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }while(this.currentThread.isAlive());
        this.songPosition--;
        if(this.songPosition<0) {
            this.songPosition=this.songList.size()-1;
        }
        this.playCurrentySong();

    }

    /**
     * This method updates the songList.
     */
    public void updateSongList() {
        this.songList = new ArrayList<String>();
        File folder = new File(this.musicFolder);
        for(File f : folder.listFiles()) {
            System.out.println(f.getAbsolutePath());
            if(f.isFile()) {
                this.songList.add(f.getName());
            }
        }
    }

    /**
     * This method plays the current song
     */
    public void playCurrentySong() {
        if(this.musicFolder.lastIndexOf("/")!=this.musicFolder.length()-1 &this.musicFolder.lastIndexOf("\\")!=this.musicFolder.length()-1) {
            this.musicFolder = this.musicFolder+"/";
        }
        else if(this.musicFolder.lastIndexOf("\\")!=this.musicFolder.length()-1) {
            this.musicFolder = this.musicFolder+"\\";
        }
        System.out.println(this.songList.size());
        this.currentSong = new Song(this.musicFolder,this.songList.get(this.songPosition));
        this.currentSong.addObserver(this);
        this.currentThread = new Thread(this.currentSong);
        this.currentThread.start();
    }
}
