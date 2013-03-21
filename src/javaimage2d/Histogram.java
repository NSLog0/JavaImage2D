/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javaimage2d;

import static com.googlecode.javacv.cpp.opencv_core.*;
import static com.googlecode.javacv.cpp.opencv_imgproc.*;
import static com.googlecode.javacv.cpp.opencv_highgui.*;
import com.sun.jna.*;
import java.util.Vector;

/**
 *
 * @author pratchaya
 */
public class Histogram {
//
//    public static IplImage apply(IplImage _image) {
//
//        IplImage hsv = cvCreateImage(cvGetSize(_image), 8, 3);
//        cvCvtColor(_image, hsv, CV_BGR2HSV);
//
//     
//
//       
//
//        IplImage r = cvCreateImage(cvGetSize(_image), 8, 1);
//        IplImage g = cvCreateImage(cvGetSize(_image), 8, 1);
//        IplImage b = cvCreateImage(cvGetSize(_image), 8, 1);
//        cvSplit(hsv, b, g, r, null);
//
//        //IplImageArray imgArray = SplitChannels.splitChannels(hsv);
//        int histSize = 256;
////
////        /// Set the ranges ( for B,G,R) )
//        int bin = 256;
//      float range[] = {0, 256};
//        float ranges[][] = {range};
//        int size[] = new int[]{bin};
//
//        boolean uniform = true;
//        boolean accumulate = false;
//       CvHistogram h = cvCreateHist(1, size, CV_HIST_ARRAY, ranges, 1);
//////
//        CvMat b_hist = null, g_hist, r_hist;
//       
//        cvGet1D(b_hist, bin);
//////        cvCalcHist(imgArray.position(0), h, bin, null);
//////         
//////        cvNo
//
//    }
}
