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
public class Guassian {

    public static IplImage apply(IplImage _image ,int _s) {

        // check has image
        // call blur image and gaussian size
        // kernel size = s3 , 5 , 7 , 9 , 11
        if (_image != null) {
            cvSmooth(_image, _image, CV_GAUSSIAN, _s);
        } else {
            System.out.println("error");
        }
        return _image;
    }
}
