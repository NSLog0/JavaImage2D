/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pratchaya.cv.imgproc;

import static com.googlecode.javacv.cpp.opencv_core.*;
import static com.googlecode.javacv.cpp.opencv_imgproc.*;
import static com.googlecode.javacv.cpp.opencv_highgui.*;
import java.nio.ByteBuffer;

/**
 *
 * @author pratchaya
 */
public class FindABContext {

    public static int[] MaxContext(IplImage _image , double c) {
        IplImage rice = cvCloneImage(_image);

        int a = 0, length = 0, haft = 0, x = 0, y = 0, z = 0;
        int[] max = new int[2];
        int start = 0, end = 0;
        boolean s = true;
        CvMat mat = new CvMat();
        cvGetMat(rice, mat, null, 1);
        MaxLoop:
        for (y = 0; y <= rice.height() - 1; y++) {
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

            if (a == 0) {
                continue MaxLoop;
            } else {
                end = start + a;
                z = (int) (((start + a) - start) / 2);
                haft = end - start;
                length = ((start + a) - start);
                max[0] = z + start;
                max[1] = y;
                break;
            }

        }



        return max;

    }

    public static int[] minContext(IplImage _image ,double c) {
        IplImage rice = cvCloneImage(_image);
        int a = 0, length = 0, haft = 0, x = 0, y = 0, z = 0;
        int[] min = new int[2];
        int start = 0, end = 0;
        boolean s = true;

        CvMat mat = new CvMat();
        cvGetMat(rice, mat, null, 1);

        MinLoop:
        for (y = rice.height() - 1; y >= 0; y--) {
            for (x = rice.width() - 1; x >= 0; x--) {
                double value = mat.get(y * rice.widthStep() + x * mat.channels());
                if (value == c) {
                    a++;
                    end = x;
                    if (s) {
                        start = x;
                        s = false;
                    }
                }

            }
            if (a == 0) {
                continue MinLoop;
            } else {
//                System.out.println("Total: " + a);
//                System.out.println("Start: " + start);
//                System.out.println("End: " + end);
//                System.out.println("Center: " + ((a / 2) + end));
                z = (int) (((start + a) - start) / 2);
                haft = end - start;
                min[0] = (start + end) / 2;
                min[1] = y;
                break;
            }

        }

        return min;
    }
}
