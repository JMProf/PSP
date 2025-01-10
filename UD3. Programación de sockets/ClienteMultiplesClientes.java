import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ClienteMultiplesClientes {

    public static void main(String[] args) throws IOException {
        String Host = "localhost";
        int Puerto = 6000;// puerto remoto
        Socket Cliente = new Socket(Host, Puerto);

        // CREO FLUJO DE SALIDA AL SERVIDOR	
        DataOutputStream fsalida = new DataOutputStream(Cliente.getOutputStream());
        // CREO FLUJO DE ENTRADA AL SERVIDOR	
        DataInputStream fentrada = new DataInputStream(Cliente.getInputStream());

        // FLUJO PARA ENTRADA ESTANDAR
        Scanner sc = new Scanner(System.in);
        String cadena, eco = "";

        // ENVÍO Y RECEPCIÓN DEL MENSAJE
        do {
            System.out.print("Introduce cadena: ");
            cadena = sc.nextLine();
            fsalida.writeUTF(cadena);
            eco = fentrada.readUTF();
            System.out.println("  =>ECO: " + eco);
        } while (!cadena.trim().equals("*"));

        // CIERRE DE STREAMS Y SOCKET
        fsalida.close();
        fentrada.close();
        System.out.println("Fin del envío... ");
        Cliente.close();
    }
}
