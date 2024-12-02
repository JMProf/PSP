import java.io.*;
import java.net.*;

public class TCPejemplo1Servidor {
  public static void main(String[] arg) throws IOException {
	int numeroPuerto = 6000;// Puerto
	ServerSocket servidor = new ServerSocket(numeroPuerto);
	System.out.println("Esperando al cliente.....");
	Socket clienteConectado = servidor.accept();

	// CREO FLUJO DE ENTRADA DEL CLIENTE
	InputStream entrada = clienteConectado.getInputStream();
	DataInputStream flujoEntrada = new DataInputStream(entrada);

	// EL CLIENTE ME ENVIA UN MENSAJE
	System.out.println("Recibiendo del CLIENTE: \n\t" + flujoEntrada.readUTF());

	// CREO FLUJO DE SALIDA AL CLIENTE
	OutputStream salida = clienteConectado.getOutputStream();
	DataOutputStream flujoSalida = new DataOutputStream(salida);

	// ENVIO UN SALUDO AL CLIENTE
	flujoSalida.writeUTF("Saludos al cliente del servidor");
        
	// CERRAR STREAMS Y SOCKETS
	entrada.close();
	flujoEntrada.close();
	salida.close();
	flujoSalida.close();
	clienteConectado.close();
	servidor.close();
  }// main
}// fin
