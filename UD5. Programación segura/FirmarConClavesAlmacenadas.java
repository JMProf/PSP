import java.io.*;
import java.security.*;
import java.security.spec.*;

public class FirmarConClavesAlmacenadas {

    public static void main(String[] args) {
        try {
            generarYAlmacenarClaves();
            PrivateKey clavePrivada = obtenerClavePrivada("Clave.privada");
            PublicKey clavePublica = obtenerClavePublica("Clave.publica");

            // Firma con la clave privada el mensaje
            // Al objeto Signature se le suministran los datos a firmar
            Signature dsa = Signature.getInstance("SHA256withRSA");
            dsa.initSign(clavePrivada);
            String mensaje = "Este mensaje va a ser firmado";
            dsa.update(mensaje.getBytes());

            byte[] firma = dsa.sign(); // Mensaje firmado

            // El receptor verifica la firma con la clave pública
            verificarFirma(clavePublica, firma, mensaje);

            // Probamos a verificar la firma con un mensaje diferente
            verificarFirma(clavePublica, firma, "Este mensaje no es el original");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void generarYAlmacenarClaves() throws NoSuchAlgorithmException, IOException {

        // Genera un par de claves con RSA
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        SecureRandom random = new SecureRandom();
        keyGen.initialize(2048, random); // Tamaño de clave recomendado: 2048 bits
        KeyPair keyPair = keyGen.generateKeyPair();

        PrivateKey clavePrivada = keyPair.getPrivate();
        PublicKey clavePublica = keyPair.getPublic();

        // Guarda clave privada en archivo
        FileOutputStream fosPriv = new FileOutputStream("Clave.privada");
        fosPriv.write(clavePrivada.getEncoded());
        fosPriv.close();

        // Guarda clave pública en archivo
        FileOutputStream fosPub = new FileOutputStream("Clave.publica");
        fosPub.write(clavePublica.getEncoded());
        fosPub.close();

        System.out.println("Claves RSA generadas y exportadas correctamente.");
    }

    public static PrivateKey obtenerClavePrivada(String clavePrivada) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {

        // Lee la clave privada desde el fichero
        FileInputStream fisPriv = new FileInputStream("Clave.privada");
        byte[] bufferPriv = new byte[fisPriv.available()];
        fisPriv.read(bufferPriv);
        fisPriv.close();

        // Recupera clave privada desde datos codificados en formato PKCS8
        PKCS8EncodedKeySpec clavePrivadaSpec = new PKCS8EncodedKeySpec(bufferPriv);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey clavePrivadaRecuperada = keyFactory.generatePrivate(clavePrivadaSpec);

        return clavePrivadaRecuperada;
    }

    public static PublicKey obtenerClavePublica(String clavePublica) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {

        // Lee la clave pública desde el fichero
        FileInputStream fisPub = new FileInputStream("Clave.publica");
        byte[] bufferPub = new byte[fisPub.available()];
        fisPub.read(bufferPub);
        fisPub.close();

        // Recupera clave pública desde datos codificados en formato X509
        X509EncodedKeySpec clavePublicaSpec = new X509EncodedKeySpec(bufferPub);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey clavePublicaRecuperada = keyFactory.generatePublic(clavePublicaSpec);

        return clavePublicaRecuperada;
    }

    public static void verificarFirma(PublicKey clavepub, byte[] firma, String mensaje)
            throws NoSuchAlgorithmException, SignatureException, InvalidKeyException {
        // Al objeto Signature se le suministran los datos a verificar
        Signature verificadsa = Signature.getInstance("SHA256withRSA");
        verificadsa.initVerify(clavepub);
        verificadsa.update(mensaje.getBytes());
        boolean check = verificadsa.verify(firma);

        if (check) {
            System.out.println("Firma verificada");
        } else {
            System.out.println("Firma no verificada");
        }
    }
}
