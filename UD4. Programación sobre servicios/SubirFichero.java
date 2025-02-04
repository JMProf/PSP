import java.io.*;
import org.apache.commons.net.ftp.*;

public class SubirFichero {

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

            //Preparación para subir archivos nuevos a un nuevo directorio
            cliente.setFileType(FTP.BINARY_FILE_TYPE);
            String direc = "/NUEVODIRECTORIO";
            
            //Si el login ha funcionado
            if (login) {
                System.out.println("Login correcto");

                //Si el directorio no existe, se crea
                if (!cliente.changeWorkingDirectory(direc)) {
                    String directorio = "NUEVODIRECTORIO";

                    if (cliente.makeDirectory(directorio)) {
                        System.out.println("Directorio :  " + directorio + " creado ...");
                        cliente.changeWorkingDirectory(directorio);
                    } else {
                        System.out.println("No se ha podido crear el Directorio");
                        System.exit(0);
                    }

                }

                System.out.println("Directorio actual: " + cliente.printWorkingDirectory());

                //Preparo el archivo a subir
                String archivo = "D:\\CLASE\\EJEMPLO.pdf";
                BufferedInputStream in = new BufferedInputStream(new FileInputStream(archivo));

                //Subo el archivo
                if (cliente.storeFile("EJEMPLO.pdf", in)) {
                    System.out.println("Subido correctamente... ");
                } else {
                    System.out.println("No se ha podido subir el fichero... ");
                }

                //Renombrar fichero
                if(cliente.rename("EJEMPLO.pdf", "EJEMPLO1.pdf"))
                	System.out.println("Fichero renombrado");
                else
                	System.out.println("No se ha podido renombrar el fichero");

                //Eliminar fichero
                String fichero = "/NUEVODIRECTORIO/EJEMPLO.pdf";
                if(cliente.deleteFile(fichero))
                	System.out.println("Fichero eliminado");
                else
                	System.out.println("No se ha podido eliminar el fichero");

                in.close(); // Cerrar flujo
                cliente.logout();
                cliente.disconnect();
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

    }

}
