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

    public static IplImage apply(IplImage _image) {

        IplImage image = cvCloneImage(_image);
        // user  OTUSU Algorithm 
        // cvAdaptiveThreshold(_image, image, 255, CV_ADAPTIVE_THRESH_GAUSSIAN_C, CV_THRESH_BINARY, 11, 9);

        cvThreshold(_image, image, 127, 255, CV_THRESH_OTSU);
        return image;
    }
}
