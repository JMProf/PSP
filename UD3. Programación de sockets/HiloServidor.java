import java.io.*;
import java.net.*;

public class HiloServidor extends Thread {

    DataInputStream fentrada;
    DataOutputStream fsalida;
    Socket socket = null;

    public HiloServidor(Socket s) throws IOException {// CONSTRUCTOR
        socket = s;
        // se crean flujos de entrada y salida
        fsalida = new DataOutputStream(socket.getOutputStream());
        fentrada = new DataInputStream(socket.getInputStream());
    }

    public void run() {// tarea a realizar con el cliente
        String cadena = "";

        System.out.println("COMUNICO CON: " + socket.toString());

        while (!cadena.trim().equals("*")) {
            try {
                cadena = fentrada.readUTF();// obtener cadena
                fsalida.writeUTF(cadena.trim().toUpperCase());// enviar mayúscula
            } catch (IOException e) {
                e.printStackTrace();
            }
        } // fin while

        System.out.println("FIN CON: " + socket.toString());

        // cerrar streams y socket
        try {
            fsalida.close();
            fentrada.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
