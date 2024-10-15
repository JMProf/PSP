/**
 * Forzamos un error en el subproceso para capturarlo
 */

import java.io.IOException;
import java.io.InputStream;
public class GetError {
    public static void main(String[] args) throws IOException{
        try{
            // Creamos ProcessBuilder e iniciamos proceso
            Process p = new ProcessBuilder("java", "Programa inexistente").start();

            // Capturamos el error y lo mostramos
            InputStream is = p.getErrorStream();
            int c;
            while ((c = is.read()) != -1) {
                System.out.print((char) c);
            }

            // Capturamos código de salida: 0 bien ; 1 error
            int exitVal = p.waitFor();
            System.out.println("Código de salida: " + exitVal);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
