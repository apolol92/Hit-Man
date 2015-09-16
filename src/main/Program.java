package main;

import org.opencv.core.Core;
import org.opencv.core.Mat;

/**
 * Created by apolol92 on 15.09.2015.
 */
public class Program {
    public static void main(String args[]) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        //Create webcam
        Webcam wCam = new Webcam();
        //Be sure that webcam is started.. just wait some frames
        wCam.secureStart();
        //And also some ms
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //Create music player to play music from music folder..
        MusicPlayer mPlayer = new MusicPlayer("music");
        //Play the first song
        mPlayer.playCurrentySong();
        //i is used for a delay after button touched..
        int i = 0;
        //check is also used for a delay after button touched..
        boolean check = false;
        while(true) {
            //Get the current webcam frame..
            Mat img = wCam.getFrame();
            //Count the amount of rgb color 115,149,148
            System.out.println("Prev:"+ColorCounter.countColor(img,115,149,148));
            //Yeah i know it's not really performant to count again(but this is just a test) :)
            //Is button previous touched?
            if(ColorCounter.countColor(img,115,149,148)<10&check==false) {
                //Previous song
                mPlayer.prevSong();
                //ms as delay
                try {
                    Thread.sleep(2000);
                    check = true;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //Is button next touched?
            if(ColorCounter.countColor(img,139,0x6e,0x50)<10&check==false) {
                //Next touched
                mPlayer.nextSong();
                //ms as delay
                try {
                    Thread.sleep(2000);
                    check = true;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if(check==true) {
                i++;
            }
            //Reset delay
            if(i==2) {
                i = 0;
                check = false;
            }
            System.out.println("Next:"+ColorCounter.countColor(img,139,0x6e,0x50));
            System.out.println();
            System.out.println("---------------------------");
        }
    }
}
