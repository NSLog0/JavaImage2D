/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javaimage2d;

import com.googlecode.javacv.CanvasFrame;
import com.googlecode.javacv.cpp.opencv_core;


/**
 *
 * @author pratchaya
 */
public class Show {
     public static void ShowImage(opencv_core.IplImage image, String caption, int size)
    {
        if(size < 128) size = 128;
        opencv_core.CvMat mat = image.asCvMat();
        int width = mat.cols(); if(width < 1) width = 1;
        int height = mat.rows(); if(height < 1) height = 1;
        double aspect = 1.0 * width / height;
        if(height != size) { height = size; width = (int) ( height * aspect ); }
        if(width != size) width = size;
        height = (int) ( width / aspect );
        ShowImage(image, caption, width, height);
    }
    public static void ShowImage(opencv_core.IplImage image, String caption, int width, int height)
    {
        CanvasFrame canvas = new CanvasFrame(caption);   // gamma=1
        canvas.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        canvas.setCanvasSize(width, height);
        canvas.showImage(image);
    }
    
}
