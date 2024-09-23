/**
 * Ejecutar el programa Java que ejecuta Geany como un proceso
 */

import java.io.*;
public class EjecutarJavaGeany {
    public static void main(String[] args) {
        //Creamos objeto File al directorio donde está AbrirGeany
        File directorio = new File("/home/user/Documentos/IntelliJ/PSP/UD1/AbrirGeany/out/production/AbrirGeany");
        //El proceso a ejecutar es AbrirGeany
        try{
        ProcessBuilder pb = new ProcessBuilder("java", "AbrirGeany");

        //Se establece el directorio donde se encuentra el ejecutable
        pb.directory(directorio);
        System.out.printf("Directorio de trabajo: %s%n",pb.directory());

        //Se ejecuta el proceso
        Process p = pb.start();

        //obtener la salida devuelta por el proceso
            InputStream is = p.getInputStream();
            int c;
            while ((c = is.read()) != -1)
                System.out.print((char) c);
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
