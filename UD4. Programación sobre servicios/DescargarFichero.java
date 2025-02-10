import java.io.*;
import org.apache.commons.net.ftp.*;

public class DescargarFichero {

    public static void main(String[] args) {
        
        //Creación del objeto FTPClient y credenciales del usuario
        FTPClient cliente = new FTPClient();

        String servidor = "localhost";
        String user = "usuario";
        String pasw = "usuario";

        try {
            //Conexión y autenticación con el servidor FTP
            System.out.println("Conectandose a " + servidor);
            cliente.connect(servidor);
            cliente.enterLocalPassiveMode();
            boolean login = cliente.login(user, pasw);

            //Si el login ha funcionado
            if (login) {
                System.out.println("Login correcto");

                //descargar fichero
                String direc = "/NUEVODIRECTORIO";
                cliente.changeWorkingDirectory(direc);

                //stream de salida para recibir el fichero descargado
                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream("C:\\Archivo\\EJEMPLO.png")); // En Linux podría ser /home/user/Escritorio/EJEMPLO.png

                if (cliente.retrieveFile("EJEMPLO.png", out)) {
                    System.out.println("Descargado correctamente");
                } else {
                    System.out.println("No se ha podido descargar");
                }

                out.close(); // Cerrar flujo

                cliente.logout();
                cliente.disconnect();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
