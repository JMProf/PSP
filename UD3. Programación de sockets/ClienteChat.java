import javax.swing.*;

/**
 * Esta clase implementa la interfaz gráfica de un cliente de chat grupal.
 * El cliente se conecta a un servidor de chat y puede enviar y recibir mensajes
 * de otros clientes conectados al servidor.
 *
 * @author
 */
public class ClienteChat extends JFrame implements Runnable {

    // Pon aquí las variables estáticas que necesites.
    static String nombre = "";

    // Variables de la interfaz gráfica
    private JTextField JMensaje;
    private JButton JEnviar;
    private JButton JSalir;
    private JScrollPane JScrollPane;
    private JTextArea JChat;
    private JPanel ventana;

    // Constructor
    public ClienteChat() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setMinimumSize(new java.awt.Dimension(400, 300));
        setContentPane(ventana);
        JChat.setEditable(false);
        pack();
    }

    /**
     * Aquí comienza el programa cliente: en primer lugar aparece una
     * pantalla solicitando un nombre, en caso de no poner nada, el
     * programa no continúa; si se pone un nombre, se inicia la interfaz
     * gráfica.
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        // Solicitud del nombre
        try{
            while (nombre.trim().length() == 0){
                nombre = JOptionPane.showInputDialog("Introduce tu nombre o nick:");
            }
        } catch(NullPointerException npex){
            System.out.println("No se ha introducido el nombre");
        }

        // Crear y mostrar la ventana
        ClienteChat cliente = new ClienteChat();
        cliente.setTitle("Chat de " + nombre);

        // Crear el hilo para la ventana de chat
        Thread chat = new Thread(cliente);
        chat.start();

        // ******************************************
        // Continuar con el programa a partir de aquí
        // ******************************************

    }

    /**
     * El método run() debe completarse con el código correspondiente a la
     * ventana de chat, esto se hace así para poder utilizar la interfaz
     * gráfica mientras el chat recibe los mensajes y los visualiza utilizando
     * su hilo específico.
     */
    @Override
    public void run() {

    }
}
