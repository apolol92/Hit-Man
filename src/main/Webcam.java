package main;

import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;

/**
 * Created by apolol92 on 12.09.2015.
 * This webcam class use the first recognized webcam at your computer.
 */
public class Webcam {
    /**
     * This attribute will access the webcam
     */
    VideoCapture cap;

    /**
     * This constructor initalize the webcam
     */
    public Webcam() {
        /**
         * Initialize cap
         */
        cap = new VideoCapture();
        /**
         * Use webcam 1
         */
        cap.open(0);
    }

    /**
     * This method returns the current webcam-frame
     * @return frame as mat
     */
    public Mat getFrame() {
        Mat mat = new Mat();
        cap.read(mat);
        return mat;
    }

    /**
     * Just wait some frames..
     */
    public void secureStart() {
        for(int i = 0; i < 20; i++) {
            getFrame();
        }
    }
}
