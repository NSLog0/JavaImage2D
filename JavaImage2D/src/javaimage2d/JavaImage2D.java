/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javaimage2d;

import com.googlecode.javacv.*;
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
        IplImage _image = cvCloneImage(image);

        image = Guassian.apply(image, 3);
        image = Grayscale.apply(image);
        image = Threshold.apply(image, CV_THRESH_OTSU);
        image = MORPH.apply(image, 7, 3, CV_MOP_ERODE);
        image = MORPH.apply(image, 7, 3, CV_MOP_DILATE);
        image = Canny.apply(image);

        int dp = 2;
        // minimum distance between two circles
        int minDist = 50;
        // Canny high threshold
        int highThreshold = 200;
        // minimum number of votes
        int votes = 100;
        int minRadius = 25;
        int maxRadius = 100;

        CvMemStorage mem = CvMemStorage.create();

        CvSeq eclipse = cvHoughCircles(image, mem, CV_HOUGH_GRADIENT,
                dp, minDist, highThreshold, votes, minRadius, maxRadius);


        for (int i = 0; i < eclipse.total(); i++) {
            CvPoint3D32f circle = new CvPoint3D32f(cvGetSeqElem(eclipse, i));
            CvPoint center = cvPointFrom32f(new CvPoint2D32f(circle.x(), circle.y()));
            int radius = Math.round(circle.z());
            //cvCircle(image, center, radius, CV_RGB(200, 200, 200), 2, CV_AA, 0);
            cvCircle(_image, center, radius, CV_RGB(255, 0, 0), 2, CV_AA, 0);
            System.out.println(center);

        }

        // Show image on window.
        final CanvasFrame iCanvas = new CanvasFrame("Display Image", 1);
        iCanvas.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        iCanvas.showImage(image);
        final CanvasFrame _iCanvas = new CanvasFrame("Display Image", 1);
        _iCanvas.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        _iCanvas.showImage(_image);



        // deallocate memory
        // wait windows 
        cvWaitKey();
        cvReleaseImage(image);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        JavaImage2D j = new JavaImage2D();

    }
}
