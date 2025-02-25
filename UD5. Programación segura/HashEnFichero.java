import java.io.*;
import java.security.*;

public class HashEnFichero {
    public static void main(String args[]) {

        try {
            // Fichero de salida
            FileOutputStream fileout = new FileOutputStream("texto.dat");
            ObjectOutputStream dataOS = new ObjectOutputStream(fileout);

            // Se calcula el hash del texto
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            String texto = "Si suenas como un idiota, la gente espera menos de ti." +
            "¡Así que todo lo que hagas parecerá más impresionante!";

            byte dataBytes[] = texto.getBytes();
            md.update(dataBytes); // Se introduce el texto en bytes a resumir
            byte resumen[] = md.digest(); // Se calcula el resumen

            dataOS.writeObject(texto); // Se escriben los datos
            dataOS.writeObject(resumen); //se escribe el resumen

            dataOS.close();
            fileout.close();

        } catch (IOException e) {
            e.printStackTrace();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
