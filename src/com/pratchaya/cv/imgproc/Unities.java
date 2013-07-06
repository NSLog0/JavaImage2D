/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pratchaya.cv.imgproc;

import static com.googlecode.javacv.cpp.opencv_core.*;
import static com.googlecode.javacv.cpp.opencv_imgproc.*;
import static com.googlecode.javacv.cpp.opencv_highgui.*;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author pratchaya
 */
public class Unities {

    public static IplImage imageToCv(BufferedImage input) {
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
}
