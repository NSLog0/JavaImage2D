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
public class MORPH {

    public static IplImage apply(IplImage _image, int _s, int __s, int type) {
        IplImage image = cvCreateImage(cvGetSize(_image), IPL_DEPTH_8U, 1);
        int kernelSize = _s;
        int kernelAnchorOffset = __s;
        IplConvKernel kernel = cvCreateStructuringElementEx(kernelSize, kernelSize, kernelAnchorOffset, kernelAnchorOffset,
                CV_SHAPE_ELLIPSE, null);

        switch (type) {
            case CV_MOP_ERODE:
                cvErode(_image, image, kernel, 1);
                break;
            case CV_MOP_DILATE:
                cvDilate(_image, image, kernel, 1);
                break;
            default:
                System.out.println("unknown morphological operation");
        }

        return image;
    }
}
