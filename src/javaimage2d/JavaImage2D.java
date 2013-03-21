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
public class JavaImage2D {

    public JavaImage2D() {

        String fileName = "image/wl2.jpg";
        IplImage image = cvLoadImage(fileName);

        image = Gaussian.apply(image, 3);
        image = Grayscale.apply(image);
        //image = SubtracBack.apply(image);
        image = Threshold.apply(image, CV_THRESH_OTSU);

        //IplConvKernel element5 = cvCreateStructuringElementEx(5, 5, 3, 3, CV_SHAPE_ELLIPSE, null);
        //cvMorphologyEx(image, image, null, element5, CV_MOP_OPEN, 1);
        //image = MORPH.apply(image, 7, 3, CV_MOP_ERODE);
        //image = MORPH.apply(image, 5, 3, CV_MOP_DILATE);
        image = Detection.apply(image, fileName);

        // Show image on window.
       Show.ShowImage(image, "Display Result Image", image.width());

        // deallocate memory
        // wait windows 
        cvWaitKey();
        cvReleaseImage(image);


    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        System.out.println("Start Program.\n");
        JavaImage2D j = new JavaImage2D();
        System.out.println("All Done.");
//        Webcam c = new Webcam();
//        c.cameraOn();
//        
    }
}
