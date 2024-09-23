/**
 * Crear una clase que implemente método público con todos los números pares comprendidos entre un min y un max.
 * Deberéis declarar min y max como atributos privados.
 */

import java.util.Scanner;

class NumerosPares{

    // Atributos
    private int min;
    private int max;

    // Constructor
    public NumerosPares(int min, int max){
        this.min = min;
        this.max = max;
    }

    // Recorre los números entre min y max buscando los pares
    public void obtenerNumerosPares(){
        System.out.println("Los números pares entre " + min + " y " + max + " son:");
        for(int i=min; i<=max; i++){
            if(i%2 == 0){
                System.out.print(i + " ");
            }
        }
    }
}

public class ObtenerNumerosPares {
    public static void main(String[] args) {
        int min, max;

        // Scanner
        Scanner sc = new Scanner(System.in);

        // Introducir números
        System.out.println("Introduce el número min:");
        min = sc.nextInt();
        System.out.println("Introduce el número max:");
        max = sc.nextInt();

        // Crear objeto de la clase pedida y lanzar el método para obtener los pares
        NumerosPares np = new NumerosPares(min, max);
        np.obtenerNumerosPares();
    }
}
