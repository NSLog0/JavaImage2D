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
public class Gaussian {

    public static IplImage apply(IplImage _image, int _s) {

        IplImage image = cvCloneImage(_image);


        // check has image
        // call blur image and gaussian size
        // kernel size = s3 , 5 , 7 , 9 , 11

        cvSmooth(_image, image, CV_GAUSSIAN, _s);

        return image;



    }
}
