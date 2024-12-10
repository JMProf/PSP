import java.io.*;
import java.net.*;

public class TCPObjetoCliente {
    public static void main(String[] arg) throws IOException, ClassNotFoundException {
        String Host = "localhost";
        int Puerto = 6000;//puerto remoto

        System.out.println("PROGRAMA CLIENTE INICIADO....");
        Socket cliente = new Socket(Host, Puerto);

        //Flujo de entrada para objetos
        ObjectInputStream perEnt = new ObjectInputStream(cliente.getInputStream());
        //Se recibe un objeto
        Persona per1 = (Persona) perEnt.readObject();//recibo objeto
        System.out.println("Recibo: "+per1.getNombre()+" "+per1.getEdad());

        //Modifico el objeto
        per1.setNombre("Pepe");
        per1.setEdad(40);

        //FLUJO DE salida para objetos
        ObjectOutputStream perSal = new ObjectOutputStream(cliente.getOutputStream());
        // Se envía el objeto
        perSal.writeObject(per1);
        System.out.println("Envio: "+per1.getNombre()+" "+per1.getEdad());

        // CERRAR STREAMS Y SOCKETS
        perEnt.close();
        perSal.close();
        cliente.close();
    }
}
