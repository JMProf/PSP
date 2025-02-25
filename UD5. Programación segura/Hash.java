import java.security.*;

public class Hash {
    public static void main(String[] args) {

        String texto = "Esto es un texto plano.";
        String texto2 = "Otro texto diferente. Aunque sea más largo, el resumen es del mismo tamaño.";

        operacionesHash(texto);
        System.out.println("\n----------------------\n");
        operacionesHash(texto2);
    }

    // Convierte un array de bytes a representación hexadecimal
    static String Hexadecimal(byte[] resumen) {
        String hex = "";
        for (int i = 0; i < resumen.length; i++) {
            String h = Integer.toHexString(resumen[i] & 0xFF);
            if (h.length() == 1) {
                hex += "0";
            }
            hex += h;
        }
        return hex.toUpperCase();
    }

    // Calcula el resumen de un texto plano y muestra información sobre el resumen
    static void operacionesHash(String texto) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-256");
            byte dataBytes[] = texto.getBytes();
            md.update(dataBytes); // Se introduce el texto en bytes a resumir
            byte resumen[] = md.digest(); // Se calcula el resumen

            System.out.println("Mensaje original: " + texto);
            System.out.println("Número de bytes: " + md.getDigestLength());
            System.out.println("Algoritmo: " + md.getAlgorithm());

            System.out.println("Mensaje resumen: " + new String(resumen));
            System.out.println("Mensaje en Hexadecimal: " + Hexadecimal(resumen));
            Provider proveedor = md.getProvider();
            System.out.println("Proveedor: " + proveedor.toString());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
