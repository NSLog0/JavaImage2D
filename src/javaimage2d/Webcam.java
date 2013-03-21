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
public class Webcam {

    public void cameraOn() throws FrameGrabber.Exception {
        OpenCVFrameGrabber g = new OpenCVFrameGrabber(0);
        g.start();
        IplImage capture = g.grab();


        CanvasFrame _iCanvas = new CanvasFrame("On Air", 1);
        _iCanvas.setCanvasSize(640, 480);
        _iCanvas.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        while (_iCanvas.isActive() == true) {
            capture = g.grab();
            _iCanvas.showImage(capture);
        }

    }
}
