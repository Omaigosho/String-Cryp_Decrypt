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

    public static int[][] submatrix(int[][] matrix, int row, int col){
        int len = matrix.length;
        int[][] submatrix = new int[len-1][len-1];
        ////////////
        int countI = 0;
        for (int i = 0; i<matrix.length; i++){
            if(i == row){
                continue;
            }
            int countJ = 0;
            for (int j = 0; j<matrix[i].length; j++){
                if(j == col){
                    continue;
                }
                submatrix[countI][countJ] = matrix[i][j];
                countJ++;
            }
            countI++;
        }
        //////////// 
        return submatrix;
    }

    // determinante de la matriz
    public static int det(int[][] matrix) {
        int len = matrix.length;

        if  (len == 1 ){
            return Math.floorMod(matrix[0][0], 29);
        }

        if (len == 2){
            int determinant = (matrix[0][0] * matrix[1][1]) - (matrix[0][1] * matrix[1][0]);
        return Math.floorMod(determinant, 29);
        }

        int total = 0;

        for (int y = 0; y<len; y++){
            int[][] sub = submatrix(matrix, 0, y);
            int detSub = det(sub);

            int sign = 0;
            int mod = Math.floorMod(y, 2);
            switch (mod) {
                case 0:
                    sign = 1;
                    break;
                default:
                    sign = -1;
                    break;
            }

            int prod = sign*detSub*matrix[0][y];
            total = total + prod;
        }


        return Math.floorMod(total, 29);
        
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
       int len = matrix.length;
       int[][] adjTrans = new int[len][len];

       int det = det(matrix);
       int detInv = inverse(det);

       //cofactors
        for(int x = 0; x<len;x++){
            for(int y = 0; y<len;y++){
                int[][] sub = submatrix(matrix, x, y);
                int detSub = det(sub);


                int sign = 0;
                int mod = Math.floorMod(x+y, 2);
                switch (mod) {
                    case 0:
                        sign = 1;
                        break;
                    default:
                        sign = -1;
                        break;
                }

                int cof = Math.floorMod(sign * detSub, 29);
                adjTrans[y][x] = Math.floorMod(cof*detInv, 29);

        }
        }
        return adjTrans;
    }

     public static String cypher(String text, int[][] base) {
        int n = base.length; 
    
        int[][] segmentedText = Alphabet.segment(text, n);
        int[][] cypherText = new int[segmentedText.length][n];

        int row = 0;
        for (int[] item : segmentedText) {
            int[] cypherVectorArr = MatrixLogic.multMatrix(base, item);
            cypherText[row] = cypherVectorArr;
            row++;
        }
        return Alphabet.restructureText(cypherText);
    }

    public static String decypher(String text, int[][] base) {
        int n = base.length; 
        int[][] segmentedText = Alphabet.segment(text, n);
        int[][] matInv = MatrixLogic.matInverse(base);
        int[][] deCypherText = new int[segmentedText.length][n];

        int row = 0;
        for (int[] item : segmentedText) {
            int[] cypherVectorArr = MatrixLogic.multMatrix(matInv, item);
            deCypherText[row] = cypherVectorArr;
            System.out.println(java.util.Arrays.toString(cypherVectorArr));
            row++;
        }
        return Alphabet.restructureText(deCypherText);
    }
}
