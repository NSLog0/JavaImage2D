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

        String fileName = "C:\\Documents and Settings\\pratchaya\\Desktop\\t.jpg";
        IplImage image = cvLoadImage(fileName);
        IplImage dest = cvCreateImage(cvSize(image.width(), image.height()), image.depth(), image.nChannels());
        IplImage result = cvCreateImage(cvSize(image.width(), image.height()), image.depth(), image.nChannels());

        //IplImage destbin = cvCreateImage(cvSize(image.width(), image.height()), image.depth(), image.nChannels());
        dest = PerspectiveTransform.apply(image, image);
        result = cvCloneImage(dest);
        dest = Gaussian.apply(dest, 3);
        cvAddWeighted(dest, 1.5, dest, -0.5, 0, dest);
        dest = Grayscale.apply(dest);
        dest = Threshold.apply(dest);
        dest = VerticalRotate.apply(dest, result);
        //cvSaveImage("C:\\Documents and Settings\\pratchaya\\Desktop\\test.jpg", dest);

        // add sharpness
        //dst = Gaussian.apply(image, 3);
        //cvAddWeighted(dst, 1.5, dst, -0.5, 0, dst);
        //dst = Canny.apply(dst);

        //add sharpness
        //CvMat kernel = CvMat.create(3, 3, CV_32FC1);
        //kernel.put(0, 9, 0, 9, -36, 9, 0, 9, 0);
        // cvFilter2D(image, image, kernel, new CvPoint(-1, -1));

        //  image = FindContours.apply(image, fileName);
        // Show image on window.
        cvShowImage(":", dest);

        // deallocate memory
        // wait windows 
        cvWaitKey();
        cvReleaseImage(image);
        cvReleaseImage(dest);
        cvReleaseImage(result);


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
