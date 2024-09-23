/**
 * Ejecutar ls como un subproceso y capturar su salida
 */

import java.io.*;
public class VerSalidaLs {
    public static void main(String[] args) {
        try{
            // Iniciamos proceso
            ProcessBuilder pb = new ProcessBuilder("ls", "/home/jm/Escritorio");
            Process process = pb.start();

            // Iniciamos InputStream para capturar la salida del subproceso carácter a carácter
            InputStream is = process.getInputStream();
            int c;
            while((c = is.read()) != -1){
                System.out.print((char) c);
            }
            is.close();

            // Comprobamos el valor de salida del subproceso (0 es código correcto)
            int exitVal;
            exitVal =  process.waitFor();
            System.out.println("Valor de salida: " + exitVal);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
