/**
 * Recibe String y lo muestra por pantalla
 */

import java.util.Scanner;
public class Echo{
    public static void main (String [] args) {
        Scanner sc = new Scanner(System.in); // Scanner para escribir desde teclado

        // Recibe texto desde el teclado
        System.out.println("Introduce un String:");
        String texto = sc.nextLine();

        // Lo muestra por pantalla
        System.out.println("String escrito: " + texto);
    }
}
