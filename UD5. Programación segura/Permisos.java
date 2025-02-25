import java.io.*;
import java.nio.file.*;

public class Permisos {

    public static void main(String[] args) {
        // Ruta del archivo
        String rutaArchivo = "/home/jm/Escritorio/fichero.txt";

        try {
            // Comprobar si el archivo es accesible
            Path path = Paths.get(rutaArchivo);
            if (!Files.isWritable(path)) {
                System.out.println("No tienes permisos de escritura en " + rutaArchivo);
                return;
            }
            if (!Files.isReadable(path)) {
                System.out.println("No tienes permisos de lectura en " + rutaArchivo);
                return;
            }

            // Escritura en fichero
            BufferedWriter fichero = new BufferedWriter(new FileWriter(rutaArchivo));
            fichero.write("Escritura de una linea en fichero.");
            fichero.newLine(); // Escribe un salto de línea
            fichero.close();
            System.out.println("Final proceso de escritura...");

            // Lectura en fichero
            BufferedReader fichero2 = new BufferedReader(new FileReader(rutaArchivo));
            String linea = fichero2.readLine();
            System.out.println("Contenido del fichero: ");
            System.out.println("\t" + linea);
            fichero2.close();
            System.out.println("Final proceso de lectura...");

        } catch (FileNotFoundException fn) {
            System.out.println("No se encuentra el fichero");
        } catch (IOException io) {
            System.out.println("Error de E/S ");
        } catch (Exception e) {
            System.out.println("ERROR => " + e.toString());
        }
    }
}
