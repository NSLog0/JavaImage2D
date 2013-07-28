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
    private int thres[], blur;
    private boolean view;

    public Classification() {
    }

    public void classifier() {

        double dist = distance(this.resource.width());
        double cm = 29.718;
        IplImage clonebin = cvCreateImage(cvGetSize(this.resource), this.resource.depth(), this.resource.nChannels());
        IplImage bin = cvCreateImage(cvGetSize(this.resource), this.resource.depth(), this.resource.nChannels());
        IplImage dest = cvCloneImage(this.resource);

        if (this.view == true) {
            dest = PerspectiveTransform.apply(this.resource, this.resource);
        }
        clonebin = cvCloneImage(dest);
        clonebin = Grayscale.apply(clonebin);
        clonebin = Gaussian.apply(clonebin, blur);
        clonebin = Threshold.apply(clonebin, this.thres[0], this.thres[1]);


        bin = cvCloneImage(clonebin);
        CvSeq contours = new CvSeq(null);
        CvMemStorage memory = CvMemStorage.create();
        cvFindContours(clonebin, memory, contours, Loader.sizeof(CvContour.class), CV_RETR_LIST, CV_CHAIN_APPROX_SIMPLE);
        CvRect r;
        CvFont font = new CvFont();
        CvFont font2 = new CvFont();

        cvInitFont(font, CV_FONT_HERSHEY_DUPLEX, 0.01, 0.7, 1, 1, 8);
        cvInitFont(font2, CV_FONT_HERSHEY_DUPLEX, 0.9, 0.2, 1, 1, 8);
        while (contours != null && !contours.isNull()) {
            if (contours.elem_size() > 0) {
                r = cvBoundingRect(contours, 1);
                int x = r.x(), y = r.y(), w = r.width(), h = r.height();
                IplImage rice = cvCreateImage(cvSize(w + 8, h + 8), bin.depth(), bin.nChannels());
                if (x == 1 || y == 1) {
                    break;
                } else {
                    cvSetImageROI(bin, cvRect(x - 5, y - 5, w + 8, h + 8));
                    cvCopy(bin, rice);
                    cvResetImageROI(bin);

                    VerticalRotate v = new VerticalRotate();
                    rice = v.apply(rice, r);
                    IplImage _rice = Threshold.apply(rice, 10, 20);
                    int max[] = FindABContext.MaxContext(_rice, 20);
                    int min[] = FindABContext.minContext(_rice, 20);

                    int vertical = (int) Math.sqrt(Math.pow(min[1] - max[1], 2) - Math.pow(min[0] - max[0], 2));
                    // cvLine(rice, cvPoint(max[0], max[1]), cvPoint(min[0], min[1]), CV_RGB(255, 0, 0), 1, 1, 0);
                    //  int [] hegi = hegi(rice, 20, vertical);

                    System.out.println("---------------");
                    //System.out.println("h: "+ (hegi[0] - hegi[1]));
                    System.out.println("in computer px: " + vertical);
                    System.out.printf("width in real(mm): %.2f\n", cm / bin.width() * vertical);

                    java.text.DecimalFormat dfm = new java.text.DecimalFormat("0.0");
                    String text = "" + dfm.format(cm / bin.width() * vertical);

                    total++;

                    if (cm / bin.width() * vertical >= 0.65 && cm / bin.width() * vertical <= 0.80) {
                        unbroken++;
                        cvPutText(dest, text, new CvPoint(x, y), font, CvScalar.WHITE);
                    } else {
                        broken++;
                        cvPutText(dest, text, new CvPoint(x, y), font, CvScalar.RED);
                    }


                }
                //cvShowImage(":", rice);
                //cvWaitKey();
                contours = contours.h_next();
            }
        }

        cvPutText(dest, "Broken:" + broken, new CvPoint(1, 20), font2, CvScalar.RED);
        cvPutText(dest, "Unbroken:" + unbroken, new CvPoint(1, 40), font2, CvScalar.WHITE);
        cvPutText(dest, "Total:" + total, new CvPoint(1, 60), font2, cvScalar(0, 255, 200, 0));
        setResource(dest);
        dest2 = getResource();

    }

    public void setImgProc(int c[], int g, boolean t) {
        setBlur(g);
        setThres(c);
        setView(t);
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
     * @param blur the blur to set
     */
    public void setBlur(int blur) {
        this.blur = blur;
    }

    /**
     * @param view the view to set
     */
    public void setView(boolean view) {
        this.view = view;
    }

    /**
     * @param thres the thres to set
     */
    public void setThres(int[] thres) {
        this.thres = thres;
    }

    public static int[] hegi(IplImage _image, double c, int w) {
        IplImage rice = cvCloneImage(_image);

        int a = 0, length = 0, haft = 0, x = 0, y = 0, z = 0;
        int[] max = new int[2];
        int start = 0, end = 0;
        boolean s = true;
        CvMat mat = new CvMat();
        cvGetMat(rice, mat, null, 1);
        MaxLoop:
        for (y = w / 2; y <= rice.height() - 1; y++) {
            for (x = 0; x <= rice.width() - 1; x++) {
                double value = mat.get(y * rice.widthStep() + x * mat.channels());
                if (value == c) {

                    a++;

                    if (s) {
                        start = x;
                        s = false;
                    }
                }

                if (x == 0) {
                    if (value == c) {
                        end = x;
                    } else {
                        end = x;
                    }
                }
            }
            break;
        }

        max[0] = start;
        max[1] = end;



        return max;

    }
}
