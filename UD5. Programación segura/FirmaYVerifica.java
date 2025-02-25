import java.security.*;

public class FirmaYVerifica {
    public static void main(String[] args) {

        try {
            // Se inicializa el generador de claves
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA");
            SecureRandom numero = SecureRandom.getInstance("SHA1PRNG");
            keyGen.initialize(2048, numero);

            // Se crean las claves pública y privada
            KeyPair par = keyGen.generateKeyPair();
            PublicKey clavepub = par.getPublic();
            PrivateKey clavepriv = par.getPrivate();

            // Firma con la clave privada el mensaje
            // Al objeto Signature se le suministran los datos a firmar
            Signature dsa = Signature.getInstance("SHA256withDSA");
            dsa.initSign(clavepriv);
            String mensaje = "Este mensaje va a ser firmado";
            dsa.update(mensaje.getBytes());

            byte[] firma = dsa.sign(); // Mensaje firmado

            // El receptor verifica la firma con la clave pública
            verificarFirma(clavepub, firma, mensaje);

            // Probamos a verificar la firma con un mensaje diferente
            verificarFirma(clavepub, firma, "Este mensaje no es el original");

        } catch (NoSuchAlgorithmException | SignatureException | InvalidKeyException e) {
            e.printStackTrace();
        }
    }

    public static void verificarFirma(PublicKey clavepub, byte[] firma, String mensaje)
            throws NoSuchAlgorithmException, SignatureException, InvalidKeyException {
        // Al objeto Signature se le suministran los datos a verificar
        Signature verificadsa = Signature.getInstance("SHA256withDSA");
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
