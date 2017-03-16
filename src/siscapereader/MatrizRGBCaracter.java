package siscapereader;

/**
 *
 * @author Roger
 */
public class MatrizRGBCaracter {

    private int x;
    private int y;
    private int[][] matriz = null;

    public MatrizRGBCaracter(int x, int y) {
        this.matriz = new int[x][y];
    }

    public int[][] getMatriz() {
        return matriz;
    }

    public void setMatriz(int[][] matriz) {
        this.matriz = matriz;
    }

    public void setPixel(int x, int y, int value) {
        int[][] temp = getMatriz();
        temp[x][y] = value;
        this.setMatriz(temp);
    }

    public void soutMatrix(Size size) {
        int valor;
        String sout = "";
        int[][] xy = getMatriz();
        for (int x = 0; x < size.getyEnd(); x++) {
            for (int y = 0; y < size.getyEnd(); y++) {
                valor = xy[x][y];
                sout += valor;
            }
            System.out.println(sout);
            sout = "";
        }
        System.out.println("");
    }
            }
