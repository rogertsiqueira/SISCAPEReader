package siscapereader;

/**
 *
 * @author Roger
 */
import java.awt.Component;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import siscapereader.compare.CompareLetras;
import siscapereader.raster.FindTop;
import siscapereader.webService.ServicoWeb;

public class ImageToMatrizRGB extends Component {

    public static void main(String[] foo) {
        //new ImageToMatrizRGB();
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

    private void marchThroughImage(BufferedImage image) {
        MatrizRGBCaracter m = null;
        List<MatrizRGBCaracter> lst = new ArrayList<MatrizRGBCaracter>();
        int cont = 0;
        int w = image.getWidth(), w2 = 0;
        int h = image.getHeight(), h2 = 0, hAux = 0;
        System.out.println("width, height: " + w + ", " + h);
        String matriz = "";
        Boolean carac = false, car = false;
        Size size = null;
        for (int i = 0; i < w; i++) {
            matriz = "";
            for (int j = 0; j < h; j++) {
                int pixel = image.getRGB(i, j);
//                int x = printPixelARGB(pixel);
//                image.setRGB(i, j, x < 100 ? 0 : 1000);
                int x = printPixelARGB(pixel);
                if (x < 90) {
                    cont++;
                    System.out.print(x + " ");
                    if (m == null) {
//                        vou usar h e h para criar a matrix pois nao serah necessario maior que isso
                        size = FindTop.getTop(image, i, image.getHeight());
                        m = new MatrizRGBCaracter(220, 220);
                        hAux = size.getyInit();
                        //JOptionPane.showMessageDialog(null, "HAUX: " + hAux + "W2: " + w2);
                    }
                    carac = true;
                    car = true;
                    x = 1;
//                    try {
                    m.setPixel(h2, w2, x);
//                    } catch (ArrayIndexOutOfBoundsException ex) {
//                        //JOptionPane.showMessageDialog(null, h2);
//                        System.exit(1);
//                    }
                }
                matriz = x + matriz;
                if ((j + 1) == h) {
                    if (!carac) {
                        if (car) {
                            if (cont > 1000) {
                                m.soutMatrix(size);
                                lst.add(m);
                            }
                            cont = 0;
                            car = false;
                        } else {
                            m = null;
                        }
                        w2 = 0;
                        h2 = 0;
                    }
                }
                h2 += 1;
            }
            h2 = 0;
            w2 += 1;
            carac = false;
        }
        CompareLetras comp = new CompareLetras();
        comp.compare(lst);
//        ServicoWeb.gravarPlaca(comp.compare(lst), "CARRO");
        //      JOptionPane.showMessageDialog(null, comp.compare(lst));
    }

    public ImageToMatrizRGB(String path) {
//    public ImageToMatrizRGB() {
        try {
            //JOptionPane.showMessageDialog(null, path);
            // get the BufferedImage, using the ImageIO class
            //BufferedImage image = ImageIO.read(getClass().getResource("img/1.jpg"));
            //BufferedImage image = ImageIO.read(new File("C:\\Documents and Settings\\Roger\\Desktop\\img_.jpg"));
            BufferedImage image = ImageIO.read(new File(path));
//            image = image.getSubimage(0, 340, 640, 140);
//            ImageIO.write(image, "JPG", new File("C:\\Documents and Settings\\Roger\\Desktop\\teste.jpg"));

            marchThroughImage(image);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
