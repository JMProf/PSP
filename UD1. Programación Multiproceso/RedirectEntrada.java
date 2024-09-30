/**
 * Se ejecuta un script como fichero de entrada del proceso
 */

import java.io.File;
import java.io.IOException;

public class RedirectEntrada {
    public static void main(String args[]) throws IOException {
        ProcessBuilder pb = new ProcessBuilder("bash");

        File fBat = new File("script.sh");
        File fOut = new File("salida.txt");
        File fErr = new File("error.txt");

        pb.redirectInput(fBat);
        pb.redirectOutput(fOut);
        pb.redirectError(fErr);
        pb.start();
    }
}
