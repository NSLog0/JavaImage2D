/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javaimage2d;

import static com.googlecode.javacv.cpp.opencv_core.*;
import static com.googlecode.javacv.cpp.opencv_highgui.*;
import static com.googlecode.javacv.cpp.opencv_imgproc.*;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author pratchaya
 */
public class JavaImage2D {

    public IplImage imageToCv(BufferedImage input) {
        if (input.getType() != BufferedImage.TYPE_BYTE_GRAY && input.getType() != BufferedImage.TYPE_3BYTE_BGR) {
            // convert it to a BufferedImage with a component order that matches the OpenCV order
            BufferedImage tmp = new BufferedImage(input.getWidth(), input.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
            Graphics2D g = tmp.createGraphics();
            g.drawImage(input, 0, 0, null);
            g.dispose();
            input = tmp;
        }
        return IplImage.createFrom(input);
    }

    public JavaImage2D() {

        String fileName = "C:\\Documents and Settings\\pratchaya\\Desktop\\20130617_235420.jpg";
        IplImage image = cvLoadImage(fileName);

        CvMat kernel = CvMat.create(3, 3, CV_32FC1);
        kernel.put(0, 9, 0, 9, -36, 9, 0, 9, 0);
        //  kernel.put(0, -3, 0);
        // kernel.put(-3, 21, -3);
        //   kernel.put(0, -3, 0);



        cvFilter2D(image, image, kernel, new CvPoint(-1, -1));
        //BufferedImage i = new BufferedImage(image.width(), image.height(), BufferedImage.TYPE_INT_BGR);;
        //i = image.getBufferedImage();
        // image = imageToCv(i);
        //image = Gaussian.apply(image, 3);
        //cvAddWeighted(image, 1.5, image, -0.5, 0, image);

        // image = Grayscale.apply(image);

        //  //image = SubtracBack.apply(image);
        //   image = Threshold.apply(image);

        //IplConvKernel element5 = cvCreateStructuringElementEx(5, 5, 3, 3, CV_SHAPE_ELLIPSE, null);
        //cvMorphologyEx(image, image, null, element5, CV_MOP_OPEN, 1);
        //image = MORPH.apply(image, 5, 3, CV_MOP_ERODE);
        //image = MORPH.apply(image, 1, 0, CV_MOP_DILATE);
        //  image = FindContours.apply(image, fileName);
        // Show image on window.

        cvShowImage(":", image);

        //System.out.println(i.getColorModel());
        // deallocate memory
        // wait windows 
        cvWaitKey();
        cvReleaseImage(image);
        //cvReleaseMat(image);


    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        System.out.println("Start Program.");
        JavaImage2D j = new JavaImage2D();
        System.out.println("All Done.");


    }
}
