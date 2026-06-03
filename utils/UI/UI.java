package utils.UI;

import java.util.Scanner;

public class UI {
    public static void menuUser() {
        System.out.println("/////////////////////////////////");
        System.out.println("CIFRADO DE MENSAJES - MOD29 \n");
        System.out.println("ELIJE UNA OPCIÓN DEL MENU: \n");
        System.out.println("1. Cifrar");
        System.out.println("2. Descifrar");
        System.out.println("3. Matriz aleatoria cuadrada");
        System.out.println("4. Verificar inversa");
        System.out.println("5. Buscar inversa");
        System.out.println("6. Salir");
        System.out.println("/////////////////////////////////");
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
                    break;
                case 2:
                    System.out.println("\n------DECYPHER DE TEXTO------");
                    break;
                case 3:
                    System.out.println("\n------MATRIZ RANDOM------");
                    break;
                case 4:
                    System.out.println("\n------VERIFICAR INVERSA DE MATRIZ------");
                    break;
                case 5:
                    System.out.println("\n------INVERSO DE DETERMINANTE EN Z_29------");
                    break;
                case 6:
                    System.out.println("\n----------SALIENDO DEL PROGRAMA...---------");
                    break;
                default:
                    System.out.println("\n/////////OPCION ERRONEA, INTENTE DE NUEVO//////////");
                    break;
            }
        }
    }

}
