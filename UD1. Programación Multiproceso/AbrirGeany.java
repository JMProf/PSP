/**
 * Crear un proceso que ejecute el programa "Geany" en Linux.
 */

public class AbrirGeany {
    public static void main(String[] args) {
        try{
            ProcessBuilder pb = new ProcessBuilder("geany");
            Process process = pb.start();
            process.waitFor();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
