/**
 * El proceso hijo comparte entrada, salida y salida de errores con el padre
 */

import java.io.*;

public class Redirect {
    public static void main(String args[]) throws IOException {
        File directorio = new File("/home/jm/Documentos/IntelliJ/PSP/UD1/Redirect/out/production/Redirect");
        ProcessBuilder pb = new ProcessBuilder("java", "HolaNombre");
        pb.directory(directorio);

        // La salida se ve en la consola
        pb.redirectInput(ProcessBuilder.Redirect.INHERIT);
        pb.redirectOutput(ProcessBuilder.Redirect.INHERIT);
        pb.redirectError(ProcessBuilder.Redirect.INHERIT);
        Process p = pb.start();
    }
}
