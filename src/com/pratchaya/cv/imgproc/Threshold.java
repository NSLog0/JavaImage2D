/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pratchaya.cv.imgproc;

import static com.googlecode.javacv.cpp.opencv_core.*;
import static com.googlecode.javacv.cpp.opencv_imgproc.*;

/**
 *
 * @author pratchaya
 */
public class Threshold {

    public static IplImage apply(IplImage _image , int s , int e) {

        IplImage image = cvCloneImage(_image);
       
        cvThreshold(_image, image, s, e, CV_THRESH_BINARY);
        
        return image;
    }
}
