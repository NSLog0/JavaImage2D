/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javaimage2d;

import com.googlecode.javacv.Blobs;

import static com.googlecode.javacv.cpp.opencv_core.*;
import static com.googlecode.javacv.cpp.opencv_imgproc.*;
import static com.googlecode.javacv.cpp.opencv_highgui.*;

/**
 *
 * @author pratchaya
 */
public class Detection {

    public static IplImage apply(IplImage _image, String path) {

        IplImage dest = cvLoadImage(path);
        IplImage WorkingImage = cvCloneImage(_image);

        int MinArea = 80;
        int ErodeCount = 4;
        int DilateCount = 1;


        cvErode(WorkingImage, WorkingImage, null, ErodeCount);
        cvDilate(WorkingImage, WorkingImage, null, DilateCount);

        Blobs Regions = new Blobs();
        Regions.BlobAnalysis(
                WorkingImage, // image
                -1, -1, // ROI start col, row
                -1, -1, // ROI cols, rows
                0, // border (0 = black; 1 = white)
                MinArea);                   // minarea
        Regions.PrintRegionData();

        for (int i = 1; i <= Blobs.MaxLabel; i++) {
            double[] Region = Blobs.RegionData[i];
            int Parent = (int) Region[Blobs.BLOBPARENT];
            int Color = (int) Region[Blobs.BLOBCOLOR];
            int MinX = (int) Region[Blobs.BLOBMINX];
            int MaxX = (int) Region[Blobs.BLOBMAXX];
            int MinY = (int) Region[Blobs.BLOBMINY];
            int MaxY = (int) Region[Blobs.BLOBMAXY];
            Highlight(dest, MinX, MinY, MaxX, MaxY, 1);
        }

        return dest;
    }

    public static void Highlight(IplImage image, int xMin, int yMin, int xMax, int yMax, int Thick) {
        CvPoint pt1 = cvPoint(xMin, yMin);
        CvPoint pt2 = cvPoint(xMax, yMax);
        CvScalar color = cvScalar(0, 0, 255, 0);       // blue [green] [red]
        cvRectangle(image, pt1, pt2, color, Thick, 4, 0);
    }
}
