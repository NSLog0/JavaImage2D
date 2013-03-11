package javaimage2d;

import static com.googlecode.javacv.cpp.opencv_core.*;
import static com.googlecode.javacv.cpp.opencv_imgproc.*;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */

/**
 *
 * @author pratchaya
 */
public class Grayscale {

    public static IplImage apply(IplImage _image) {
        IplImage image = cvCloneImage(_image);
        // We need a grayscale image in order to do the recognition, so we
        // create a new image of the same size as the original one.
        IplImage grayImage = cvCreateImage(cvGetSize(_image), IPL_DEPTH_8U, 1);
        cvCvtColor(image, grayImage, CV_RGB2GRAY);
        return grayImage;
    }
}
