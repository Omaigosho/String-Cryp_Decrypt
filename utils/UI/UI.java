package utils.UI;

import java.util.Scanner;

import utils.MatrixLogic;

public class UI {
    public static void menuUser() {
        System.out.println("/////////////////////////////////");
        System.out.println("CIFRADO DE MENSAJES - MOD29 \n");
        System.out.println("ELIJE UNA OPCIÓN DEL MENU: \n");
        System.out.println("1. Cifrar");
        System.out.println("2. Descifrar");
        System.out.println("3. Matriz aleatoria cuadrada");
        System.out.println("4. Verificar inverso de matriz");
        System.out.println("5. Salir");
        System.out.println("/////////////////////////////////");
    }

    public static int[][] matrixCells(Scanner sc, int n){
        int[][] matrix = new int[n][n];
        System.out.println("\n------INGRESO DE LA MATRIZ DE CODIFICACION " + n + "x" + n);

        for(int x = 0; x<n;x++){
            for(int y = 0; y<n;y++){
                System.out.print("VALOR PARA LA CELDA [" + x + "][" + y + "]: ");
                while(!sc.hasNextInt()){
                    System.out.println("DEBES INGRESAR UN NUMERO ENTERO");
                    System.out.print("CELDA [" + x + "][" + y + "]:");
                    sc.next();
                }
                int value = sc.nextInt();
                matrix[x][y] = Math.floorMod(value, 29);
            }
        }
        sc.nextLine();
        return matrix;
    }

    public static void matrixShow(int[][] matrix){
        if (matrix == null){
            System.out.println("MATRIZ VACIA");
            return;
        }

        System.out.println();
        for(int x = 0; x<matrix.length;x++){
            System.out.print(" |");
            for(int y = 0; y<matrix[x].length;y++){
                System.out.printf("%2d", matrix[x][y]);
            }
            System.out.println("|");
        }
        System.out.println();
    }
    
    public static void System(){
        Scanner sc = new Scanner(System.in);
       boolean repeat = true;

        int[][] clave = null;
        int dim = 2;

        while(repeat){
            UI.menuUser();

            if(!sc.hasNextInt()){
                System.out.println("\n-----INGRESE UN NUMERO VALIDO-----");
                sc.nextLine();
                continue;
            }

            int op = sc.nextInt();
            sc.nextLine();

            switch (op) {
                case 1:
                    System.out.println("\n------CIFRADO DE TEXTO------");

                    if (clave == null){
                        System.out.println("Matriz de cifrado vacia");
                        System.out.println("INGRESE LA DIMENSION DE LA MATRIZ");
                        dim = sc.nextInt();
                        sc.nextLine();
                        clave = matrixCells(sc, dim);
                    }
                    System.out.println("\nMatriz Actual: ");
                    matrixShow(clave);

                    int det = MatrixLogic.det(clave);

                    if (det == 0){
                        System.out.println("DETERMINANTE = 0, NO EXISTE MATRIZ INVERSA");
                        clave = null;
                    }

                    System.out.println("INGRESA EL TEXTO QUE QUIERES CIFRAR: ");
                    String text = sc.nextLine();
                    String textCypher = MatrixLogic.cypher(text, clave);
                    System.out.println("TEXTO CIFRADO: \""+ textCypher + "\"");
                    break;
                case 2:
                    System.out.println("\n------DECYPHER DE TEXTO------");

                    if (clave == null){
                        System.out.println("Matriz de cifrado vacia");
                        dim = sc.nextInt();
                        sc.nextLine();
                        clave = matrixCells(sc, dim);
                    }

                    System.out.println("\nMatriz Actual: ");
                    matrixShow(clave);

                    int detDec = MatrixLogic.det(clave);

                    if (detDec == 0){
                        System.out.println("DETERMINANTE = 0, NO EXISTE MATRIZ INVERSA");
                        clave = null;
                    }

                    System.out.println("INGRESA EL TEXTO QUE QUIERES DESCIFRAR: ");
                    String textDec = sc.nextLine();
                    String textDecypher = MatrixLogic.decypher(textDec, clave);
                    System.out.println("TEXTO DESCIFRADO: \""+ textDecypher + "\"");
                    break;
                case 3:
                    System.out.println("\n------MATRIZ RANDOM------");

                    System.out.println("INGRESE LA DIMENSION DE LA MATRIZ: ");
                    dim = sc.nextInt();
                    sc.nextLine();
                    int [][] randMatrix = new int[dim][dim];

                    while(MatrixLogic.inverse(MatrixLogic.det(randMatrix)) == 0){
                        for(int x = 0; x<dim;x++){
                            for(int y =0; y<dim;y++){
                                randMatrix[x][y] = (int)(Math.random() * 29);
                            }
                        }
                    }
                    clave = randMatrix;
                    matrixShow(randMatrix);
                    break;
                case 4:
                    System.out.println("\n------VERIFICAR INVERSA DE MATRIZ------");

                    if(clave == null){
                        System.out.println("MATRIZ DE CIFRADO VACIA");
                    } else{
                        System.out.println("MATRIZ DE CIFRADO ACTUAL:");
                        matrixShow(clave);

                        System.out.println("INVERSA EN MOD 29:");
                        matrixShow(MatrixLogic.matInverse(clave));
                    }
                    break;
                case 5:
                    System.out.println("\n----------SALIENDO DEL PROGRAMA...---------");
                    System.exit(0);
                    break;
                default:
                    System.out.println("\n/////////OPCION ERRONEA, INTENTE DE NUEVO//////////");
                    break;
            }
        }
    }

}
