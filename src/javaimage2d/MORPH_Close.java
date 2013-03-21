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
public class MORPH_Close {

    public static IplImage apply(IplImage _image, int _s, int __s) {
        IplImage image = cvCloneImage(_image);
        int kernelSize = _s;
        int kernelAnchorOffset = __s;
        IplConvKernel kernel = cvCreateStructuringElementEx(kernelSize, kernelSize, kernelAnchorOffset, kernelAnchorOffset,
                CV_SHAPE_ELLIPSE, null);
        Close:
        {
            MORPH.apply(_image, _s, __s, MORPH_DILATE);
            MORPH.apply(_image, _s, __s, MORPH_ERODE);
        }
        return image;
    }
}
