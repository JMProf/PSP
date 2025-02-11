import java.io.IOException;
import java.io.Writer;
import java.security.InvalidKeyException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.spec.InvalidKeySpecException;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import org.apache.commons.net.smtp.*;

public class ClienteSMTP {

    public static void main(String[] args) throws NoSuchAlgorithmException, UnrecoverableKeyException,
            KeyStoreException, InvalidKeyException, InvalidKeySpecException {

        // Se crea objeto cliente SMTP seguro
        AuthenticatingSMTPClient client = new AuthenticatingSMTPClient();

        // Datos del usuario y del servidor
        String server = "servidorSMTP";
        String username = "correo@dominio.com";
        String password = "contraseña";
        int puerto = 587;
        String remitente = username;

        try {

            /******************
            PRUEBAS DE CONEXIÓN
            ******************/

            // Prueba de conexión al servidor SMTP (sin loggin)
            client.connect(server, puerto);
            System.out.println("1 - " + client.getReplyString()); // Este método recupera el último mensaje devuelto por el servidor

            // Se puede recibir también el código de respuesta
            int respuesta = client.getReplyCode();
            if (!SMTPReply.isPositiveCompletion(respuesta)) {
                client.disconnect();
                System.err.println("CONEXIÓN RECHAZADA.");
                System.exit(1);
            }

            // Se puede iniciar el protocolo intercambiando caracterísitcas de la comunicación entre cliente y servidor
            client.ehlo(server);
            System.out.println("2 - " + client.getReplyString());

            /*******************
            AUTENTICACIÓN SEGURA
            *******************/

            // Creación de la clave para establecer un canal seguro
            KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            kmf.init(null, null);
            KeyManager km = kmf.getKeyManagers()[0];

            // Se asocia la clave generada al objeto cliente
            client.setKeyManager(km);



            // Negociación TLS - Modo no implícito - Puerto 587
            if (client.execTLS()) {
                System.out.println("3 - " + client.getReplyString());
                client.ehlo(server); // Es necesario iniciar la comunicación con un mensaje EHLO

                // Se realiza la autenticación con el servidor
                if (client.auth(AuthenticatingSMTPClient.AUTH_METHOD.LOGIN, username, password)) {
                    System.out.println("4 - " + client.getReplyString());

                    /*****************
                    CREACIÓN DEL EMAIL
                    *****************/

                    String destino = "destino@dominio.com";
                    String asunto = "Prueba de SMTPClient con Java";
                    String mensaje = "Hola. \nEnviando saludos.\nUsando  Java.\nChao.";

                    // Se crea la cabecera del email
                    SimpleSMTPHeader cabecera = new SimpleSMTPHeader(remitente, destino, asunto);

                    // El nombre de usuario y el email de origen coinciden (normalmente)
                    client.setSender(remitente);
                    client.addRecipient(destino);
                    System.out.println("5 - " + client.getReplyString());

                    /**************
                    ENVÍO DEL EMAIL
                    **************/
                    
                    /*
                    Se notifica al servidor que se comienza a escribir el correo
                    Devuelve un objeto Writer (flujo o stream) si el servidor acepta. Este objeto servirá para enviar el mensaje
                    */
                    Writer writer = client.sendMessageData();
                    if (writer == null) { // Fallo
                        System.out.println("FALLO AL COMENZAR EL EMAIL.");
                        System.exit(1);
                    }

                    // Se envía el mensaje
                    writer.write(cabecera.toString()); // Cabecera
                    writer.write(mensaje);// Cuerpo del mensaje
                    writer.close();
                    System.out.println("6 - " + client.getReplyString());

                    // Se comprueba si se ha enviado correctamente
                    boolean exito = client.completePendingCommand();
                    System.out.println("7 - " + client.getReplyString());

                    if (!exito) { // Fallo
                        System.out.println("FALLO AL FINALIZAR TRANSACCIÓN.");
                        System.exit(1);
                    } else {
                        System.out.println("MENSAJE ENVIADO CON EXITO......");
                    }

                } else {
                    System.out.println("USUARIO NO AUTENTICADO.");
                }
            } else {
                System.out.println("FALLO AL EJECUTAR USAR TLS.");
            }
            
        } catch (IOException e) {
            System.err.println("No se ha podido conectar al servidor.");
            e.printStackTrace();
            System.exit(1);
        }
        try {
            client.disconnect();
        } catch (IOException f) {
            f.printStackTrace();
        }

        System.out.println("Fin de envío.");
    }
}
