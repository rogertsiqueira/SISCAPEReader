package siscapereader.read.letra;

/**
 *
 * @author Roger
 */
public class IndexToChar {

//    static String[] carac = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K",
//        "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    static String[] carac = {"E", "I", "K"};

    public static String getChar(int index) {
        return carac[index - 1];
    }
}
