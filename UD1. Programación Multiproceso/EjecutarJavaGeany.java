/**
 * Ejecutar el programa Java que ejecuta Geany como un proceso
 */

import java.io.*;
public class EjecutarJavaGeany {
    public static void main(String[] args) {
        //Creamos objeto File al directorio donde está AbrirGeany
        File directorio = new File("/home/jm/Documentos/IntelliJ/PSP/UD1/AbrirGeany/out/production/AbrirGeany");
        //El proceso a ejecutar es AbrirGeany
        try{
        ProcessBuilder pb = new ProcessBuilder("java", "AbrirGeany");

        //Se establece el directorio donde se encuentra el ejecutable
        pb.directory(directorio);
        System.out.printf("Directorio de trabajo: %s%n",pb.directory());

        //Se ejecuta el proceso
        Process p = pb.start();

        // Comprobamos el valor de salida del subproceso (0 es código correcto)
            int exitVal;
            exitVal =  p.waitFor();
            System.out.println("Valor de salida: " + exitVal);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
