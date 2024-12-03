import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPservidor {
  public static void main(String[] args) throws Exception {
    byte[] buffer = new byte[1024];//buffer para recibir el datagrama 
    //ASOCIO EL SOCKET AL PUERTO 12345
    DatagramSocket socket = new DatagramSocket(12345);   

    //ESPERANDO DATAGRAMA
    System.out.println("Esperando Datagrama .......... ");  
    DatagramPacket recibo = new DatagramPacket(buffer, buffer.length);
    socket.receive(recibo);//recibo datagrama
    String paquete = new String(recibo.getData());//obtengo String

    //VISUALIZO INFORMACIÓN
    System.out.println("Número de Bytes recibidos: "+ recibo.getLength());    
    System.out.println("Contenido del Paquete    : "+ paquete.trim());
    System.out.println("Puerto origen del mensaje: "+ recibo.getPort());
    System.out.println("IP de origen             : "+ recibo.getAddress().getHostAddress());   
    System.out.println("Puerto destino del mensaje:" + socket.getLocalPort());	
    socket.close(); //cierro el socket
  }
}
