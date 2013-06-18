/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javaimage2d;

import com.googlecode.javacv.Blobs;

import static com.googlecode.javacv.cpp.opencv_core.*;
import static com.googlecode.javacv.cpp.opencv_imgproc.*;
import static com.googlecode.javacv.cpp.opencv_highgui.*;
import java.awt.Rectangle;

/**
 *
 * @author pratchaya
 */
public class Detection {

    public static IplImage apply(IplImage _image, String path) {

        IplImage dest = cvLoadImage(path);
        IplImage WorkingImage = cvCloneImage(_image);

        int MinArea = 30;
        //  int ErodeCount = 3;
        //  int DilateCount = 1;


        // cvErode(WorkingImage, WorkingImage, null, ErodeCount);
        //  cvDilate(WorkingImage, WorkingImage, null, DilateCount);
        // Show.ShowImage(WorkingImage, "", WorkingImage.width());

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
            //Rectangle rect = new Rectangle(MinX, MinY, MaxX, MaxY);
            //  CvRect rects = new CvRect(MinX, MinY, MaxX, MaxY);
            // int x = MaxX - MinX;
            // int y = MaxY - MinY;
            // CvRect re = new CvRect(x, y, 50,50);
            // cvSetImageROI(WorkingImage, re);
            // IplImage cropped = cvCreateImage(cvGetSize(WorkingImage), WorkingImage.depth(), WorkingImage.nChannels());
            // cvCopy(WorkingImage, cropped);
            // Show.ShowImage(cropped, "ROI", cropped.width());
            // cvResetImageROI(WorkingImage);

        }
        Show.ShowImage(WorkingImage, "s", WorkingImage.width());
        return dest;
    }

    public static void Highlight(IplImage image, int xMin, int yMin, int xMax, int yMax, int Thick) {
        CvPoint pt1 = cvPoint(xMin, yMin);
        CvPoint pt2 = cvPoint(xMax, yMax);
        CvScalar color = cvScalar(0, 0, 255, 0);       // blue [green] [red]
        cvRectangle(image, pt1, pt2, color, Thick, 4, 0);
    }
}
