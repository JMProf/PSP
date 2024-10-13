/**
 * Compartir entrada y salida con subproceso
 */

import java.io.*;
import java.util.Scanner; 
public class EjecutarEcho {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in); // Scanner para escribir desde teclado

        // Preparamos subproceso Echo
        File directorio = new File("/home/jm/Escritorio"); // Modificar con la ubicación del subproceso
        ProcessBuilder pb = new ProcessBuilder("java", "Echo");
        pb.directory(directorio);

        // Compartimos entrada y salida con el subproceso
        pb.redirectInput(ProcessBuilder.Redirect.INHERIT);
        pb.redirectOutput(ProcessBuilder.Redirect.INHERIT);
        
        // Se ejecuta el proceso
        Process p = pb.start();

        // Comprobamos la salida: 0 -> bien - Otro -> error
        int exitVal;
        try {
            exitVal = p.waitFor();
            System.out.println("Valor de Salida: " + exitVal);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
