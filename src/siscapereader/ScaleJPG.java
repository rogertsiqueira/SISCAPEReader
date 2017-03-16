package siscapereader;

/**
 *
 * @author Roger
 */
import java.awt.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.*;

public class ScaleJPG {

    public ScaleJPG() {
        try {
            this.setScale();
        } catch (IOException ex) {
            Logger.getLogger(ScaleJPG.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//    public static void scale(String src, int width, int height, String dest) throws IOException {
    public static void scale(File src, int width, int height, File dest) throws IOException {
        BufferedImage bsrc = ImageIO.read(dest);
        BufferedImage bdest = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = bdest.createGraphics();
        AffineTransform at = AffineTransform.getScaleInstance((double) width / bsrc.getWidth(), (double) height / bsrc.getHeight());
        g.drawRenderedImage(bsrc, at);
        ImageIO.write(bdest, "JPG", dest);


//        ImageOutputStream ios;
//        ByteArrayOutputStream baos=new ByteArrayOutputStream();
//        ImageIO.write(bdest, "JPG", baos);
//        baos.wr
//        baos.toByteArray();
//        ImageIO.re
    }

    public void setScale() throws IOException {
        //scale("C:\\Documents and Settings\\Roger\\Desktop\\img.jpg", 555, 126, "C:\\Documents and Settings\\Roger\\Desktop\\sai.jpg");
    }
}
