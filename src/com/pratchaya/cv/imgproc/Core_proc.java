package com.pratchaya.cv.imgproc;

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
        String fileName = "C:\\Documents and Settings\\pratchaya\\My Documents\\GitHub\\JavaImage2D\\image\\r2.png"; //"C:\\Documents and Settings\\pratchaya\\Desktop\\m403.jpg";
        IplImage image = cvLoadImage(fileName);
        IplImage dest = cvCreateImage(cvGetSize(image), 8, 1);

        //dest = PerspectiveTransform.apply(image, image);
        Classification c = new Classification();
        c.setResource(image);
        c.setImgProc(109, 5);
        c.classifier();

        // deallocate memory
        // wait windows 
        //cvShowImage(":", dest);
        cvWaitKey();

        cvReleaseImage(image);
        cvReleaseImage(dest);

    }

    public static void main(String[] args) {
        System.out.println("Start Program.");
        Core_proc j = new Core_proc();
        System.out.println("All Done.");
    }
}
