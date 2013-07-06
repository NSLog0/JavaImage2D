/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pratchaya.cv.imgproc;

import com.googlecode.javacpp.Loader;
import static com.googlecode.javacv.cpp.opencv_core.*;
import static com.googlecode.javacv.cpp.opencv_imgproc.*;
import static com.googlecode.javacv.cpp.opencv_highgui.*;

/**
 *
 * @author pratchaya
 */
public class Classification {

    private IplImage resource;
    IplImage dest2;
    double avgTotal;
    double agvLose;
    int total;
    int unbroken;
    int broken;
    private int thres, blur;

    public Classification() {
    }

    public void classifier() {
        double dist = distance(this.resource.width());
        double cm = 29.718;
        IplImage clonebin = cvCloneImage(this.resource);
        IplImage bin = cvCreateImage(cvGetSize(this.resource), 8, 1);
        IplImage dest = cvCloneImage(this.resource);

        clonebin = Grayscale.apply(clonebin);
        clonebin = Gaussian.apply(clonebin, blur);
        clonebin = Threshold.apply(clonebin, thres);

        bin = cvCloneImage(clonebin);
        CvSeq contours = new CvSeq(null);
        CvMemStorage memory = CvMemStorage.create();
        cvFindContours(clonebin, memory, contours, Loader.sizeof(CvContour.class), CV_RETR_LIST, CV_CHAIN_APPROX_SIMPLE);
        CvRect r;

        while (contours != null && !contours.isNull()) {
            if (contours.elem_size() > 0) {
                r = cvBoundingRect(contours, 0);
                cvRectangle(dest, cvPoint(r.x(), r.y()), cvPoint(r.x() + r.width(), r.y() + r.height()), CV_RGB(255, 255, 0), 1, 0, 0);

                IplImage rice = cvCreateImage(cvSize(r.width(), r.height()), clonebin.depth(), clonebin.nChannels());
                cvSetImageROI(bin, cvRect(r.x(), r.y(), r.width(), r.height()));
                cvCopy(bin, rice);
                //cvcop
                cvResetImageROI(bin);

                // rice = VerticalRotate.apply(rice, r);

                cvShowImage(":", rice);
                cvWaitKey();

                contours = contours.h_next();
            }
        }


    }

    public void setImgProc(int c, int g) {
        setBlur(g);
        setThres(c);
    }

    public static double distance(int _w) {
        int c = 19200;
        int w = _w;
        double dis = (double) c / (double) w;
        return dis;
    }

    /**
     * @return the resource
     */
    public IplImage getResource() {
        return resource;
    }

    /**
     * @param resource the resource to set
     */
    public void setResource(IplImage resource) {
        this.resource = resource;
    }

    /**
     * @param thres the thres to set
     */
    public void setThres(int thres) {
        this.thres = thres;
    }

    /**
     * @param blur the blur to set
     */
    public void setBlur(int blur) {
        this.blur = blur;
    }
}
