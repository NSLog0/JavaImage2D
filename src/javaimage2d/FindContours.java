/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javaimage2d;

import com.googlecode.javacpp.Loader;
import static com.googlecode.javacv.cpp.opencv_core.*;
import static com.googlecode.javacv.cpp.opencv_imgproc.*;
import static com.googlecode.javacv.cpp.opencv_highgui.*;

/**
 *
 * @author BMC
 */
public class FindContours {

    public static IplImage apply(IplImage _image, String path) {
        IplImage dest = cvLoadImage(path);
        IplImage clonebin = cvCloneImage(_image);

        CvSeq contours = new CvSeq();
        CvSeq seq = null;
        CvMemStorage memory = CvMemStorage.create();
        cvFindContours(clonebin, memory, contours, Loader.sizeof(CvContour.class), CV_RETR_LIST, CV_CHAIN_APPROX_SIMPLE);
        CvRect r;
        int totals = 0;
        for (seq = contours; seq != null; seq = seq.h_next()) {
            r = cvBoundingRect(seq, 0);
            cvRectangle(dest, cvPoint(r.x(), r.y()), cvPoint(r.x() + r.width(), r.y() + r.height()), CV_RGB(255, 0, 0), 1, 0, 0);

            IplImage rice = cvCreateImage(cvSize(r.width(), r.height()), _image.depth(), _image.nChannels());
            cvSetImageROI(_image, cvRect(r.x(), r.y(), r.width(), r.height()));
            cvCopy(_image, rice);
            cvResetImageROI(_image);
      
//            CvMat mat = rice.asCvMat();
//
//            // find horizental 
//            if (mat.cols() > mat.rows()) {
//                IplImage trans = cvCreateImage(cvSize(mat.rows(), mat.cols()), rice.depth(), rice.nChannels());
//                cvTranspose(rice, trans);
//                cvFlip(trans, trans, 1);
//                Show.ShowImage(trans, "Result Each Object", trans.sizeof());
//                // cvSaveImage("C:\\img\\test"+(++totals)+".jpg", trans);
//            } // end if 

            // set center object 
//            CvPoint2D32f center = new CvPoint2D32f(mat.cols() / 2.0F, mat.rows() / 2.0F);
//            int x = 0, y = 0;
//            float m = (float) mat.rows() / (float) mat.cols(), m1 = -1 * m;
//            int fromleft = 0, fromright = 0;
//            while (x < mat.cols()) {
//                y = (int) m * x;
//                if ((int) mat.get(y, x) == 255) {
//                    fromleft++;
//                }
//
//                x++;
//            }
//
//            x = mat.cols();
//            while (x > 0) {
//                y = (int) m1 * x + mat.rows();
//                if ((int) mat.get(y, x) == 255) {
//                    fromright++;
//                }
//                x--;
//            }
//
//            if (fromleft >= fromright) {
//                cvLine(dest, cvPoint(r.x(), r.y()), cvPoint(r.x() + r.width(), r.y() + r.height()), CV_RGB(255, 0, 0), 1, 0, 0);
//            } else {
//                cvLine(dest, cvPoint(r.x() + r.width() / 2, r.y()), cvPoint(r.x() + r.width() / 2, r.y() + r.height()), CV_RGB(255, 0, 0), 1, 0, 0);
//            }
//
//
//            cvLine(dest, cvPoint(r.x() + r.width() / 2, r.y()), cvPoint(r.x() + r.width() / 2, r.y() + r.height()), CV_RGB(0, 0, 255), 1, 0, 0);
//
//            CvMat rot = null;
//
//
//            if (fromleft >= fromright) {
//                cv2DRotationMatrix(center, Math.atan((float) mat.cols() / mat.rows()) * -180 / 3.1415926535, 1, rot);
//
//            } else {
//                cv2DRotationMatrix(center, Math.atan((float) mat.cols() / mat.rows()) * 180 / 3.1415926535, 1, rot);
//
//            }
//
//            cvWarpAffine(rice, rice, rot);
            Show.ShowImage(rice, "Result Each Object", 20);
        }

        return dest;
    }
}
