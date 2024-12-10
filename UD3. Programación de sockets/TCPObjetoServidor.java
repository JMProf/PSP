import java.io.*;
import java.net.*;

public class TCPObjetoServidor {
    public static void main(String[] arg) throws IOException, ClassNotFoundException {
        int numeroPuerto = 6000;// Puerto
        ServerSocket servidor =  new ServerSocket(numeroPuerto);
        System.out.println("Esperando al cliente.....");
        Socket cliente = servidor.accept();

        // Se prepara un flujo de salida para objetos
        ObjectOutputStream outObjeto = new ObjectOutputStream(cliente.getOutputStream());
        // Se prepara un objeto y se envía
        Persona per1 = new Persona("Juan", 20);
        outObjeto.writeObject(per1); //enviando objeto
        System.out.println("Envio: " + per1.getNombre() +"*"+ per1.getEdad());

        // Se obtiene un stream para leer objetos
        ObjectInputStream inObjeto = new ObjectInputStream(cliente.getInputStream());
        Persona per2 = (Persona) inObjeto.readObject();
        System.out.println("Recibo: "+per2.getNombre()+"*"+per2.getEdad());

        // CERRAR STREAMS Y SOCKETS
        outObjeto.close();
        inObjeto.close();
        cliente.close();
        servidor.close();
    }
}
