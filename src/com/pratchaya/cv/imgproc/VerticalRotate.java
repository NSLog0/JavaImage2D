/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pratchaya.cv.imgproc;

import com.googlecode.javacpp.Loader;
import static com.googlecode.javacv.cpp.opencv_core.*;
import static com.googlecode.javacv.cpp.opencv_imgproc.*;
import static com.googlecode.javacv.cpp.opencv_highgui.*;
import java.nio.ByteBuffer;

/**
 *
 * @author BMCF
 */
public class VerticalRotate {

    public static IplImage rotationMatrix(IplImage _image, int fromleft, int fromright, CvPoint2D32f center, double angle) {
        IplImage dest = cvCloneImage(_image);
        CvMat rot = cvCreateMat(2, 3, CV_32FC1);

        if (fromleft >= fromright) {
            cv2DRotationMatrix(center, -angle, 1.0, rot);

        } else {
            cv2DRotationMatrix(center, angle, 1.0, rot);

        }

        //if (Math.abs(angle) > 20) {
        cvWarpAffine(dest, dest, rot);
        //  }

        return dest;
    }

    public static IplImage apply(IplImage _image, CvRect _r) {

        CvRect r = _r;
        IplImage rice = cvCloneImage(_image);
        //find horizental 
        if (rice.width() > rice.height()) {
            CvMat mat = new CvMat();
            cvGetMat(rice, mat, null, 1);
            IplImage trans = cvCreateImage(cvSize(mat.rows(), mat.cols()), rice.depth(), rice.nChannels());
            cvTranspose(rice, trans);
            cvFlip(trans, trans, 1);
            rice = null;
            rice = cvCreateImage(cvSize(trans.width(), trans.height()), trans.depth(), trans.nChannels());
            rice = cvCloneImage(trans);
        }

        //set center object 
        CvPoint2D32f center = new CvPoint2D32f(rice.width() / 2.0f, rice.height() / 2.0f);
        int x = 0, y;
        float m = (float) rice.height() / (float) rice.width(), m1 = -1 * m;
        int fromleft = 0, fromright = 0;
        ByteBuffer buffer = rice.getByteBuffer();

        CvMat mat = new CvMat();
        cvGetMat(rice, mat, null, 1);
        while (x < mat.cols()) {
            y = (int) (m * x);

            int index = x * rice.widthStep() + y;
            int value = buffer.get(index) & 0xFF;
            if (value == 255) {
                fromleft++;
            }
            x++;
        }

        x = mat.cols();
        while (x > 0) {
            y = (int) m1 * x + mat.rows();

            int index = y * rice.widthStep() + x;
            int value = buffer.get(index) & 0xFF;
            if (value == 255) {
                fromright++;
            }
            x--;
        }

        //  cvLine(dest, cvPoint(r.x() + r.width() / 2, r.y()), cvPoint(r.x() + r.width() / 2, r.y() + r.height()), CV_RGB(0, 0, 255), 1, 0, 0);
        double angle = Math.atan((double) mat.cols() / (double) mat.rows()) * 180 / 3.1415926535;
        rice = rotationMatrix(rice, fromleft, fromright, center, angle);

        int[] max = FindABContext.MaxContext(rice);
        int[] min = FindABContext.minContext(rice);

        // cvLine(rice, cvPoint(max[0], max[1]), cvPoint(min[0], min[1]), CV_RGB(255, 0, 0), 1, 0, 0);

        angle = Math.atan((double) (min[1] - max[1]) / (min[0] - max[0])); // หมุน
        double ang = 90 - Math.toDegrees(angle);
        if (min[0] > max[0]) {
            ang *= -1;
        }

        rice = rotationMatrix(rice, fromleft, fromright, center, ang);


        return rice;

    }
}
