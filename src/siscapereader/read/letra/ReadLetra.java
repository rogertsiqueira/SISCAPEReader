package siscapereader.read.letra;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import siscapereader.MatrizRGBCaracter;
import siscapereader.Size;
import siscapereader.raster.FindTop;

/**
 *
 * @author Roger
 */
public class ReadLetra {

    public int[][] get(String pkg, String letra) throws IOException {
        //JOptionPane.showMessageDialog(null, "lendo letra: " + letra);
        System.out.println("lendo letra: " + letra);
        URL url = getClass().getResource("../../img/" + pkg + "/" + letra + ".JPG");
        MatrizRGBCaracter m = null;
        BufferedImage image = ImageIO.read(url);
        int w = image.getWidth(), xtemp = -1;
        int h = image.getHeight(), hAux = 0;
        String matriz = "";
        Boolean carac = false, car = false;
        Size size = null;
        for (int x = 0; x < w; x++) {
            matriz = "";
            for (int y = 0; y < h; y++) {
                int pixel = image.getRGB(x, y);
                int a = printPixelARGB(pixel);
                if (a < 65) {
                    if (m == null) {
//                        vou usar h e h para criar a matrix pois nao serah necessario maior que isso
                        size = FindTop.getTop(image, x, image.getHeight());
                        m = new MatrizRGBCaracter(220, 220);
                        hAux = size.getyInit();
                        //JOptionPane.showMessageDialog(null, ("y: " + y + " haux: " + hAux));
                        xtemp = x;
                    }
                    carac = true;
                    car = true;
                    a = 1;
                    m.setPixel(y - hAux, x - xtemp, a);
                }
                matriz = x + matriz;
                if ((y + 1) == h) {
                    if (!carac) {
                        if (car) {
                            m.soutMatrix(size);
                            car = false;
                        }
                    }
                }
            }
            carac = false;
        }
        return m.getMatriz();
    }

    public int printPixelARGB(int pixel) {
        int alpha = (pixel >> 24) & 0xff;
        int red = (pixel >> 16) & 0xff;
        int green = (pixel >> 8) & 0xff;
        int blue = (pixel) & 0xff;
        int cinza = (red + green + blue) / 3;
        //System.out.println("argb: " + alpha + ", " + red + ", " + green + ", " + blue);
        return cinza;
    }
}
