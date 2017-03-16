package siscapereader.compare;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import siscapereader.MatrizRGBCaracter;
import siscapereader.read.letra.IndexToChar;
import siscapereader.read.letra.ReadLetra;

/**
 *
 * @author Roger
 */
public class CompareLetras {

    private String lst = "";
    private int cont = 1;

    public String compare(List<MatrizRGBCaracter> list) {
        for (MatrizRGBCaracter m : list) {
            read(m.getMatriz());
            //JOptionPane.showMessageDialog(null, lst);
        }
        JOptionPane.showMessageDialog(null, lst);
        //        System.out.println("ACABOU");
        return lst;
    }

    private void read(int[][] carac) {
        if (cont < 4) {
            for (int l = 1; l <= 3; l++) {
                if (!readLetra(carac, l, false).isEmpty()) {
                    break;
                }
            }
        } else if (cont == 4) {
            lst += "-6";
            cont++;
            return;
        } else {
            for (int l = 0; l <= 9; l++) {
                if (!readLetra(carac, l, true).isEmpty()) {
                    break;
                }
            }
        }
    }

    private String readLetra(int[][] carac, int l, Boolean num) {
        int a = 0;
        int b = 0;
        int qtPixel = 200 * 200;
        try {
            String letra = "";
            int[][] i = null;
            if (!num) {
                letra = IndexToChar.getChar(l);
                i = new ReadLetra().get("letras", letra);
                for (int x = 0; x < 200; x++) {
                    for (int y = 0; y < 200; y++) {
                        a = carac[x][y];
                        b = i[x][y];
                        if (a + b == 1) {
                            qtPixel -= 1;
                        }
                    }
                }
            } else {
                if (l == 0 || l == 5 || l == 6) {
                    letra = String.valueOf(l);
                    i = new ReadLetra().get("number", letra);
                    for (int x = 0; x < 200; x++) {
                        for (int y = 0; y < 200; y++) {
                            a = carac[x][y];
                            b = i[x][y];
                            if (a + b == 1) {
                                qtPixel -= 1;
                            }
                        }
                    }
                } else {
                    return "";
                }
            }

//            for (int x = 0; x < 200; x++) {
//                for (int y = 0; y < 200; y++) {
//                    a = carac[x][y];
//                    b = i[x][y];
//                    if (a + b == 1) {
//                        qtPixel -= 1;
//                    }
//                }
//            }
            //JOptionPane.showMessageDialog(null, "QT PIXELS" + qtPixel);
            System.out.println("QT PIXELS" + qtPixel);
            if (qtPixel > 35000) {
                lst += letra;
                cont++;
                return letra;
            }
        } catch (IOException ex) {
            Logger.getLogger(CompareLetras.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
}
