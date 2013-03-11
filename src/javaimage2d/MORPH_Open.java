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
public class MORPH_Open {

    public static IplImage apply(IplImage _image, int _s, int __s) {
        IplImage image = cvCreateImage(cvGetSize(_image), _image.depth(), 1);
        int kernelSize = _s;
        int kernelAnchorOffset = __s;
        IplConvKernel kernel = cvCreateStructuringElementEx(kernelSize, kernelSize, kernelAnchorOffset, kernelAnchorOffset,
                CV_SHAPE_RECT, null);
        Open:
        {
            MORPH.apply(_image, _s, __s, MORPH_ERODE);
            MORPH.apply(_image, _s, __s, MORPH_DILATE);
        }
        return image;
    }
}
