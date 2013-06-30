/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javaimage2d;

import static com.googlecode.javacv.cpp.opencv_core.*;
import com.googlecode.javacv.cpp.opencv_core.CvPoint2D32f;
import static com.googlecode.javacv.cpp.opencv_imgproc.*;
import static com.googlecode.javacv.cpp.opencv_highgui.*;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author pratchaya
 */
public class JavaImage2D {

    public JavaImage2D() {


        String fileName = "C:\\Documents and Settings\\pratchaya\\Desktop\\403.jpg";
        IplImage image = cvLoadImage(fileName);
        IplImage dest = cvCreateImage(cvGetSize(image), 8, 1);
        dest = PerspectiveTransform.apply(image, image);

        //image = Grayscale.apply(image);
        //image = Gaussian.apply(image, 5);
        //image = Threshold.apply(image);
        //System.out.println(dest.width() + " " + dest.height());
        dest = VerticalRotate.apply(dest);
        //dest = Grayscale.apply(dest);
        //dest = Gaussian.apply(dest, 5);
        //dest = Threshold.apply(dest);
        // Show image on window.
        cvShowImage(":", dest);
        //cvSaveImage("C:\\Documents and Settings\\pratchaya\\Desktop\\g.jpg", dest);

        // deallocate memory
        // wait windows 
        cvWaitKey();

        cvReleaseImage(image);
        cvReleaseImage(dest);

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
