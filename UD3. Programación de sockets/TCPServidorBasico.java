import java.io.IOException;
import java.net.*;

public class TCPServidorBasico {
    public static void main(String[] args) throws IOException {
        int Puerto = 6000;// Puerto 
        ServerSocket Servidor = new ServerSocket(Puerto);

        System.out.println("Escuchando en " + Servidor.getLocalPort());	
        Socket cliente= Servidor.accept();//esperando a un cliente 

        //realizar acciones con cliente
      
        Servidor.close();//Cierro socket servidor
    }
}
