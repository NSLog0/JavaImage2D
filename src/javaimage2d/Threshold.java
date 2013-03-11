/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javaimage2d;

import static com.googlecode.javacv.cpp.opencv_core.*;
import static com.googlecode.javacv.cpp.opencv_imgproc.*;

/**
 *
 * @author pratchaya
 */
public class Threshold {

    public static IplImage apply(IplImage _image, int type) {
        IplImage image = cvCloneImage(_image);

        // user  OTUSU Algorithm 
        // cvAdaptiveThreshold(_image, image, 255, CV_ADAPTIVE_THRESH_GAUSSIAN_C, CV_THRESH_BINARY, 11, 9);

        switch (type) {
            case CV_THRESH_OTSU:
                cvThreshold(_image, image, 127, 255, CV_THRESH_OTSU);
                break;
            case CV_THRESH_MASK:
                cvThreshold(_image, image, 127, 255, CV_THRESH_MASK);
                break;
            case CV_THRESH_TRUNC:
                cvThreshold(_image, image, 127, 255, CV_THRESH_TRUNC);
                break;
            default:
                System.out.println("unknown thresh operation");

        }


        return image;
    }
}
