package utils;

public class MatrixLogic {

    // multiplicar matriz y vector
    public static int[] multMatrix(int[][] matrix, int[] vector) {
        int[] resVector = new int[matrix.length];
        int cofactor;
        for (int i = 0; i < matrix.length; i++) {
            cofactor = 0;
            for (int x = 0; x < matrix[i].length; x++) {
                cofactor = cofactor + (matrix[i][x] * vector[x]);
            }
            resVector[i] = Math.floorMod(cofactor, 29);
        }
        return resVector;
    }

    // determinante de la matriz
    public static int det(int[][] matrix) {
        int determinant = (matrix[0][0] * matrix[1][1]) - (matrix[0][1] * matrix[1][0]);
        return Math.floorMod(determinant, 29);
    }

    // inverso del determinante
    public static int inverse(int determinant) {
        int inv = 0;
        for (int i = 1; i < 29; i++) {
            if (Math.floorMod(determinant * i, 29) == 1) {
                inv = i;
            }
        }
        return inv;
    }

    // inverso de la matriz
    public static int[][] matInverse(int[][] matrix) {
        int a = matrix[0][0];
        int b = matrix[0][1];
        int c = matrix[1][0];
        int d = matrix[1][1];

        int[][] adj = {{d, -b}, {-c, a}};
        int detInv = inverse(det(matrix));
        for (int x = 0; x < adj.length; x++) {
            for (int y = 0; y < adj[x].length; y++) {
                adj[x][y] = Math.floorMod(adj[x][y] * detInv, 29);
            }
        }
        return adj;
    }
}
