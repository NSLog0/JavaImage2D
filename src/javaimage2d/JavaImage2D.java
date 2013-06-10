/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javaimage2d;

import static com.googlecode.javacv.cpp.opencv_core.*;
import static com.googlecode.javacv.cpp.opencv_highgui.*;

/**
 *
 * @author pratchaya
 */
public class JavaImage2D {

    public JavaImage2D() {

        String fileName = "image\\r2.png";
        IplImage image = cvLoadImage(fileName);

        image = Gaussian.apply(image, 7);
        image = Grayscale.apply(image);
        //  //image = SubtracBack.apply(image);
        image = Threshold.apply(image);

        //IplConvKernel element5 = cvCreateStructuringElementEx(5, 5, 3, 3, CV_SHAPE_ELLIPSE, null);
        //cvMorphologyEx(image, image, null, element5, CV_MOP_OPEN, 1);
        // image = MORPH.apply(image, 11, 3, CV_MOP_ERODE);
        // image = MORPH.apply(image, 5, 3, CV_MOP_DILATE);
        image = FindContours.apply(image, fileName);
        // Show image on window.

        cvShowImage(":", image);
        // deallocate memory
        // wait windows 
        cvWaitKey();
        cvReleaseImage(image);
        //cvReleaseMat(image);


    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        System.out.println("Start Program.");
        JavaImage2D j = new JavaImage2D();
        System.out.println("All Done.");


    }
}
