import utils.MatrixLogic;
import utils.Alphabet;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static String cypher(String text, int[][] base, int n) {
        List<List<Integer>> segmentedText = Alphabet.segment(text, n);
        List<List<Integer>> cypherText = new ArrayList<>();
        for (List<Integer> item : segmentedText) {

            int[] vector = new int[item.size()];
            for (int i = 0; i < item.size(); i++) {
                vector[i] = item.get(i);
            }
            int[] cypherVectorArr = MatrixLogic.multMatrix(base, vector);

            List<Integer> cypherVector = new ArrayList<>();
            for (int val : cypherVectorArr) {
                cypherVector.add(val);
            }
            cypherText.add(cypherVector);
        }
        return Alphabet.restructureText(cypherText);
    }

    public static String decypher(String text, int[][] base, int n) {
        List<List<Integer>> segmentedText = Alphabet.segment(text, n);
        int[][] matInv = MatrixLogic.matInverse(base);
        List<List<Integer>> deCypherText = new ArrayList<>();
        for (List<Integer> item : segmentedText) {
            int[] vector = new int[item.size()];
            for (int i = 0; i < item.size(); i++) {
                vector[i] = item.get(i);
            }
            int[] cypherVectorArr = MatrixLogic.multMatrix(matInv, vector);

            List<Integer> cypherVector = new ArrayList<>();
            for (int val : cypherVectorArr) {
                cypherVector.add(val);
            }
            deCypherText.add(cypherVector);
        }
        return Alphabet.restructureText(deCypherText);
    }

    public static void main(String[] args) {
        int[][] base = { { 2, 1 }, { 3, 4 } };
        String text = "HELLO WORLD";
        int n = 2;

        System.out.println("Original: " + text);

        String encrypted = cypher(text, base, n);
        System.out.println("Encrypted: " + encrypted);

        String decrypted = decypher(encrypted, base, n);
        System.out.println("Decrypted: '" + decrypted + "'");
    }

}
