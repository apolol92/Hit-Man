package main;

import org.opencv.core.Mat;

/**
 * Created by apolol92 on 15.09.2015.
 * This class contains a static method which count one given rgb color in an image..
 * It also has got a private class which checks if it the same rgb color..
 */
public class ColorCounter {

    /**
     * Counts the apperense of a given color
     * @param img
     * @param r
     * @param g
     * @param b
     * @return amount pixels of the given color
     */
    public static int countColor(Mat img, int r, int g, int b) {
        int amount=0;
        for(int row = 0; row < img.rows(); row++) {
            for(int col = 0; col < img.cols(); col++) {
                if(isSameColor(r,g,b,img.get(row,col))) {
                    amount++;
                }
            }
        }
        return amount;
    }

    /**
     * This method check is it the same color..
     * Modify the if-clause for a optimized comparison..
     * @param r
     * @param g
     * @param b
     * @param other rgb color as double array, because img returns rgb color as double array..
     * @return true if the same color..
     */
    private static boolean isSameColor(int r, int g, int b, double[] other) {
        if(Math.abs(r-other[0])<3 & Math.abs(g-other[1])<3 & Math.abs(b-other[2])<3) {
            return true;
        }
        return false;
    }


}
