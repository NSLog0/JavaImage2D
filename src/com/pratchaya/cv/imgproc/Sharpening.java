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
public class Sharpening {

    public static IplImage apply(IplImage _image) {
        IplImage dest = cvCloneImage(_image);
        CvMat kernel = CvMat.create(3, 3, CV_32F);
        kernel.put(1, 1, 5);
        kernel.put(0, 1, -1);
        kernel.put(2, 1, -1);
        kernel.put(1, 0, -1);
        kernel.put(1, 2, -1);
        filter2D(_image, dest, -1, kernel, new CvPoint(-1, -1), 0, BORDER_DEFAULT);
        
        return  dest;
    }
}
