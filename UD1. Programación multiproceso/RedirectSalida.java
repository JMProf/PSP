/**
 * Comando ls (o un programa inexistente) devuelve su salida o error a un fichero
 */

import java.io.File;
import java.io.IOException;

public class RedirectSalida {
    public static void main(String args[]) throws IOException {
        ProcessBuilder pb = new ProcessBuilder("ls");
        // ProcessBuilder pb = new ProcessBuilder("java", "javaNoExistente");

        File fOut = new File("salida.txt");
        File fErr = new File("error.txt");

        pb.redirectOutput(fOut);
        pb.redirectError(fErr);
        pb.start();
    }
}
