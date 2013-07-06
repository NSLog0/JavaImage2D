/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pratchaya.cv.imgproc;

import static com.googlecode.javacv.cpp.opencv_core.*;
import static com.googlecode.javacv.cpp.opencv_imgproc.*;
import static com.googlecode.javacv.cpp.opencv_highgui.*;

/**
 *
 * @author pratchaya
 */
public class SplitChannels {

    public static IplImageArray splitChannels(IplImage hsvImage) {
        int depth = hsvImage.depth();
        IplImage channel0 = cvCreateImage(cvGetSize(hsvImage), hsvImage.depth(), 1);
        IplImage channel1 = cvCreateImage(cvGetSize(hsvImage), hsvImage.depth(), 1);
        IplImage channel2 = cvCreateImage(cvGetSize(hsvImage), hsvImage.depth(), 1);
        cvSplit(hsvImage, channel0, channel1, channel2, null);
        return new IplImageArray(channel0, channel1, channel2);
    }
}
