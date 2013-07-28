package com.pratchaya.cv.imgproc;

import com.googlecode.javacv.CanvasFrame;
import com.googlecode.javacv.FrameGrabber;
import com.googlecode.javacv.OpenCVFrameGrabber;
import static com.googlecode.javacv.cpp.opencv_core.*;
import static com.googlecode.javacv.cpp.opencv_imgproc.*;
import static com.googlecode.javacv.cpp.opencv_highgui.*;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author pratchaya
 */
public class Core_proc {

    public Core_proc() {
        String fileName = "C:\\Documents and Settings\\pratchaya\\Desktop\\img\\M403.jpg"; //"C:\\Documents and Settings\\pratchaya\\Desktop\\m403.jpg";
        IplImage image = cvLoadImage(fileName);
        //cvShowImage(":", image);
        // cvWaitKey();

        //image = Grayscale.apply(image);
        //image = Gaussian.apply(image, 3);
        //image = Threshold.apply(image, 140, 255);

        int[] rang = {109, 255};

        Classification c = new Classification();
        c.setResource(image);
        c.setImgProc(rang, 3, true);
        c.classifier();

        //cvShowImage("final", c.dest2);
        // System.out.println("broken: " + c.broken);
        // System.out.println("Unbroken: " + c.unbroken);
        //  System.out.println("Total: " + c.total);
        cvShowImage("sdf", c.dest2);
        cvWaitKey();

        // deallocate    memory
        // wait windows 

        //cvSaveImage("C:\\Documents and Settings\\pratchaya\\Desktop\\yy.jpg", c.dest2);

        //cvShowImage("final", image);
        //cvWaitKey();
        cvReleaseImage(image);
        //cvReleaseImage(dest);

    }

    public static void main(String[] args) throws FrameGrabber.Exception {
        System.out.println("Start Program.");
        Core_proc j = new Core_proc();
        System.out.println("All Done.");
    }
}
