/**
 * Pide el nombre al usuario y le saluda
 */

import java.util.Scanner;

public class HolaNombre {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce tu nombre:");
        String nombre = sc.nextLine();
        System.out.println("Hola, " + nombre);
    }
}
