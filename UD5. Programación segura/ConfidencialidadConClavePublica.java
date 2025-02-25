import java.security.*;
import javax.crypto.*;

public class ConfidencialidadConClavePublica {

    public static void main(String args[]) {
        try {
            // Genera un par de claves con RSA
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            SecureRandom random = new SecureRandom();
            keyGen.initialize(2048, random); // Tamaño de clave recomendado: 2048 bits
            KeyPair keyPair = keyGen.generateKeyPair();

            PrivateKey clavePrivada = keyPair.getPrivate();
            PublicKey clavePublica = keyPair.getPublic();

            // Se crea la contraseña utilizando AES
            KeyGenerator kg = KeyGenerator.getInstance("AES");
            kg.init(128);
            SecretKey contrasena = kg.generateKey();

            // Se envuelve la clave con la clave pública RSA
            Cipher c = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            c.init(Cipher.WRAP_MODE, clavePublica);
            byte[] contrasenaEnvuelta = c.wrap(contrasena);

            // Se cifra el texto con la contraseña
            c = Cipher.getInstance("AES/ECB/PKCS5Padding");
            c.init(Cipher.ENCRYPT_MODE, contrasena);
            String mensaje = "Este mensaje va a ser cifrado para que únicamente su destinatario pueda leerlo.";
            byte textoPlano[] = mensaje.getBytes();
            byte textoCifrado[] = c.doFinal(textoPlano);
            System.out.println("Encriptado: " + new String(textoCifrado));

            // Se desenvuelve la clave con la clave privada RSA
            Cipher c2 = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            c2.init(Cipher.UNWRAP_MODE, clavePrivada);
            Key contrasenaDesenvuelta = c2.unwrap(contrasenaEnvuelta, "AES", Cipher.SECRET_KEY);

            // Descifra el texto con la contraseña desenvuelta
            c2 = Cipher.getInstance("AES/ECB/PKCS5Padding");
            c2.init(Cipher.DECRYPT_MODE, contrasenaDesenvuelta);
            byte desencriptado[] = c2.doFinal(textoCifrado);
            System.out.println("Desencriptado: " + new String(desencriptado));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
