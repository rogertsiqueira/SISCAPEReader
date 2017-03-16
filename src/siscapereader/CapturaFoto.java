package siscapereader;

/**
 *
 * @author Roger
 */
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Image;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.media.Buffer;
import javax.media.CaptureDeviceInfo;
import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.Player;
import javax.media.cdm.CaptureDeviceManager;
import javax.media.control.FrameGrabbingControl;
import javax.media.format.RGBFormat;
import javax.media.format.VideoFormat;
import javax.media.util.BufferToImage;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class CapturaFoto extends JPanel {

    public static Player player = null;
    public CaptureDeviceInfo di = null;  //  @jve:decl-index=0:
    public MediaLocator ml = null;  //  @jve:decl-index=0:
    public JButton capture = null;
    public Buffer buf = null;
    public Image img = null;
    public VideoFormat vf = null;
    public BufferToImage btoi = null;
    private static final long serialVersionUID = 1L;
    private JButton captura = null;

    public CapturaFoto() {
        super();
        initialize();
        getCaptura();
    }

    private void initialize() {
        String str2 = "vfw//0";
        di = CaptureDeviceManager.getDevice(str2);
        ml = new MediaLocator("vfw://0");
        try {
            player = Manager.createRealizedPlayer(ml);
            player.start();
            Component comp;
            if ((comp = player.getVisualComponent()) != null) {
                add(comp, BorderLayout.NORTH);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void gravaImg(Image imagem) {
        String caminho = "C:\\Documents and Settings\\Roger\\Desktop\\00.JPG";
        try {
            ImageIO.write((RenderedImage) imagem, "jpg", new File(caminho));
            JOptionPane.showMessageDialog(this, "Imagem Capturada!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "não foi possivel encontrar "
                    + "o dispositivo para a captura da imagem.");
            e.printStackTrace();
        }
    }

    private void getCaptura() {
        FrameGrabbingControl fgc = (FrameGrabbingControl) player.getControl("javax.media.control.FrameGrabbingControl");
        buf = fgc.grabFrame();
        RGBFormat rgb = (RGBFormat) buf.getFormat();
        btoi = new BufferToImage((VideoFormat) buf.getFormat());
        img = btoi.createImage(buf);
        gravaImg(img);
    }
    public static void main(String[] args) {
        new CapturaFoto();
    }
}
