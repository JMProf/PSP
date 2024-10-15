/**
 * Subproceso de EnviarDatos.java
 */

import java.util.Scanner;
public class LecturaDatos{
    public static void main (String [] args) {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.println("Introduce un String....");
            String texto = sc.nextLine();
            System.out.println("String escrito: " + texto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
