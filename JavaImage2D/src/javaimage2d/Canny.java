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
public class Canny {

    public static IplImage apply(IplImage _image) {

        IplImage image = cvCreateImage(cvGetSize(_image), _image.depth(), 1);
        int threshold1 = 125;
        int threshold2 = 350;
        int apertureSize = 3;
        cvCanny(_image, image, threshold1, threshold2, apertureSize);
        return image;
    }
}
