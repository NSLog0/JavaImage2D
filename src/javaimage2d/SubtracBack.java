/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javaimage2d;

import static com.googlecode.javacv.cpp.opencv_core.*;
import static com.googlecode.javacv.cpp.opencv_imgproc.*;
import static com.googlecode.javacv.cpp.opencv_highgui.*;

/**
 *
 * @author pratchaya
 */
public class SubtracBack {

    public static IplImage apply(IplImage _image) {

        IplImage image = cvCloneImage(_image);
        IplImage lastimg = cvCloneImage(_image);
        IplImage diff = cvCloneImage(_image);
        IplConvKernel element5 = cvCreateStructuringElementEx(5, 5, 3, 3, CV_SHAPE_ELLIPSE, null);
        cvMorphologyEx(lastimg, lastimg, null, element5, CV_MOP_OPEN, 1);
        cvAbsDiff(image, lastimg, diff);
        return diff;
    }
}
