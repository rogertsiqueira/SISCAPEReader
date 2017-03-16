package siscapereader;

/**
 *
 * @author Roger
 */
import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.media.Buffer;
import javax.media.CaptureDeviceInfo;
import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.Player;
import javax.media.cdm.CaptureDeviceManager;
import javax.media.control.FrameGrabbingControl;
import javax.media.format.VideoFormat;
import javax.media.util.BufferToImage;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JOptionPane;

public class _WebCanCapture extends Applet implements ActionListener {

    private static final long serialVersionUID = 1L;
    public static Player player = null;
    public CaptureDeviceInfo di = null;  //  @jve:decl-index=0:
    public MediaLocator ml = null;  //  @jve:decl-index=0:
    public JButton capture = null;
    public Buffer buf = null;
    public Image img = null;
    public VideoFormat vf = null;
    public BufferToImage btoi = null;
    public ImagePanel imgpanel = null;

    public void init() {
        //JOptionPane.showMessageDialog(null, "inicializou");
        this.setLayout(new BorderLayout());
        this.setSize(640, 480);
        this.setVisible(true);
        imgpanel = new ImagePanel();
        capture = new JButton("Capture");
        capture.setSize(25, 50);
        capture.setLocation(0, 0);
        capture.addActionListener(this);

        // This may differ check the jmf registry for
        // correct entry
        String str2 = "vfw//0";
        di = CaptureDeviceManager.getDevice(str2);
        ml = new MediaLocator("vfw://0");
        //JOptionPane.showMessageDialog(null, "executou di e ml");
        try {
            player = Manager.createRealizedPlayer(ml);
            player.start();
            Component comp;
            if ((comp = player.getVisualComponent()) != null) {
                add(comp, BorderLayout.NORTH);
            }
            add(capture, BorderLayout.NORTH);
            add(imgpanel, BorderLayout.SOUTH);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void paint(Graphics g) {
    }

    public static void playerclose() {
        player.close();
        player.deallocate();
    }

    public void actionPerformed(ActionEvent e) {
        JComponent c = (JComponent) e.getSource();
        if (c == capture) {
            // Grab a frame
            FrameGrabbingControl fgc = (FrameGrabbingControl) player.getControl("javax.media.control.FrameGrabbingControl");
            buf = fgc.grabFrame();

            // Convert it to an image
            btoi = new BufferToImage((VideoFormat) buf.getFormat());
            img = btoi.createImage(buf);
            // show the image
            imgpanel.setImage(img);
            gravaImg(img);
        }
    }

    public void gravaImg(Image imagem) {
        String caminho = "C:\\Documents and Settings\\Roger\\Desktop\\00.JPG";
        try {
            File orig = new File(caminho), dest = new File("C:\\Documents and Settings\\Roger\\Desktop\\teste.jpg");
            ImageIO.write((RenderedImage) imagem, "JPG", orig);
            cut(orig, dest);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "não foi possivel encontrar "
                    + "o dispositivo para a captura da imagem.");
            e.printStackTrace();
        }

    }
    
    private void cut(File orig, File dest) {
        try {
            BufferedImage image = ImageIO.read(orig);
            image = image.getSubimage(0, 350, 640, 130);
            ImageIO.write(image, "JPG", dest);
            ScaleJPG.scale(orig, 1100, 210, dest);
            new ImageToMatrizRGB(dest.getPath());
        } catch (IOException ex) {
            Logger.getLogger(_WebCanCapture.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    class ImagePanel extends Panel {

        private static final long serialVersionUID = 1L;
        public Image myimg = null;

        public ImagePanel() {
          //  JOptionPane.showMessageDialog(null, "setando a classe");
            setLayout(null);
            setSize(320, 240);
        }

        public void setImage(Image img) {
            this.myimg = img;
            repaint();
        }

        public void paint(Graphics g) {
            if (myimg != null) {
                g.drawImage(myimg, 0, 0, this);
            }
        }
    }
}
