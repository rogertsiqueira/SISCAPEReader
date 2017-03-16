package siscapereader.training;

/**
 *
 * @author Roger
 */
public class Training {

    public static void main(String[] args) {
        Training.getA();
        Training.soutMatrix();
    }

    public static int[][] getA() {
        int[][] A = new int[255][255];
        return A;
    }

    public static void soutMatrix() {
        int valor;
        String sout = "";
        int[][] xy = getA();
        for (int x = 0; x < 255; x++) {
            for (int y = 0; y < 255; y++) {
                valor = xy[x][y];
                sout += valor;
            }
            System.out.println(sout);
            sout = "";
        }
    }
}
