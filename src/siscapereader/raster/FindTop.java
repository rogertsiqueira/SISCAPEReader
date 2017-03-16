package siscapereader.raster;

import java.awt.image.BufferedImage;
import siscapereader.Size;

/**
 *
 * @author Roger
 */
public class FindTop {

    public static Size getTop(BufferedImage img, int w, int h) {
        Size size = new Size();
        int yInit = -1;
        int pixel = 0, a = 0, heigth = -1;
        for (int y = 0; y < h; y++) {
            for (int x = w; x < w + 70; x++) {
                pixel = img.getRGB(x, y);
                a = printPixelARGB(pixel);
                if (a < 80) {
                    if (yInit < 0) {
                        size.setyInit(y);
                        yInit = y;
                    } else {
                        size.setyEnd(y);
                    }
                }
            }
        }
        return size;
    }

    public static int printPixelARGB(int pixel) {
        int alpha = (pixel >> 24) & 0xff;
        int red = (pixel >> 16) & 0xff;
        int green = (pixel >> 8) & 0xff;
        int blue = (pixel) & 0xff;
        int cinza = (red + green + blue) / 3;
        //System.out.println("argb: " + alpha + ", " + red + ", " + green + ", " + blue);
        return cinza;
    }
}
