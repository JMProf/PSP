/**
 * Enviar datos a subproceso
 */

import java.io.*;
public class EnviarDatos {
    public static void main(String[] args) throws IOException {
        File directorio = new File("/home/jm/Documentos/IntelliJ/PSP/UD1/EnviarDatos/out/production/EnviarDatos");
        ProcessBuilder pb = new ProcessBuilder("java", "LecturaDatos");
        pb.directory(directorio);

        // Se ejecuta el proceso
        Process p = pb.start();

        // Escritura -- envia entrada
        OutputStream os = p.getOutputStream();
        os.write("Hola mundo\n".getBytes());
        os.flush(); // vacía el buffer de salida

        // Lectura -- obtiene la salida
        InputStream is = p.getInputStream();
        int c;
        while ((c = is.read()) != -1)
            System.out.print((char) c);
        is.close();

        // COMPROBACION DE ERROR: 0 bien - 1 error
        int exitVal;
        try {
            exitVal = p.waitFor();
            System.out.println("Valor de Salida: " + exitVal);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            InputStream er = p.getErrorStream();
            int ch;
            while ((ch = er.read()) != -1)
                System.out.print((char) ch);
            er.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
