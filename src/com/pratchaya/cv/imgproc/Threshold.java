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

    public static IplImage apply(IplImage _image , int c) {

       // IplImage image = cvCreateImage(cvGetSize(_image), 8, 1);
        IplImage image = cvCloneImage(_image);
        //cvAdaptiveThreshold(_image, image, 255, CV_ADAPTIVE_THRESH_MEAN_C, CV_THRESH_BINARY_INV, 5, 4);
         //CvScalar v = cvAvg(image, image);
       // System.out.println(v.magnitude());
        cvThreshold(_image, image, c, 255, CV_THRESH_BINARY);
        
        return image;
    }
}
