class HiloContador implements Runnable {
    private boolean pausar = false; // Bandera para controlar que se pause el hilo

    // Poner bandera a true para que la hebra se pause cuando la compruebe
    public synchronized void pausar() throws InterruptedException {
        pausar = true;
    }

    // Poner bandera a false y despertar a la hebra
    public synchronized void continuar() {
        pausar = false;
        notify();
    }
    /*
     Cuenta hasta 10 esperando medio segundo entre cada número
     y va comprobando si debe pausarse
     */
    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                // Si la bandera marca pausar, el hilo se suspende
                synchronized (this) {
                    while (pausar) {
                        wait();
                    }
                }

                System.out.println("Vuelta número: " + i);
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            System.out.println("Hilo interrumpida");
        }
    }
}

public class SuspendeHilo {
    public static void main (String[] args) {
        // Crea hilo
        HiloContador hiloContador = new HiloContador();
        Thread hebra = new Thread(hiloContador);

        // Comienza hilo
        hebra.start();

        try {
            // Deja que el hilo se ejecute un poco
            Thread.sleep(2000);

            // Pausa el hilo
            System.out.println("Pausando hilo...");
            hiloContador.pausar();

            // Espera un tiempo para que el hilo se suspenda
            Thread.sleep(2000);

            // Reanuda el hilo
            System.out.println("Reanudando hilo...");
            hiloContador.continuar();

            // Espera a que el hilo termine
            hebra.join();
        } catch (InterruptedException e) {
            System.out.println("Main interrumpido");
        }

        System.out.println("Hilo finalizado");
    }
}
