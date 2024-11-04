class Contador3 {
    private int c = 0; //Atributo contador
    Contador3(int c) {
        this.c = c;
    }

    public synchronized void incrementa() {
        c = c + 1;
    }

    public synchronized void decrementa() {
        c = c - 1;
    }

    public synchronized int getValor() {
        return c;
    }

}// CONTADOR

class HiloA3 extends Thread {
    private Contador3 contador;

    //Asignamos un nombre al hilo y un valor para el contador
    public HiloA3(String n, Contador3 c) {
        setName(n);
        contador = c;
    }

    //Incrementa el valor del contador en 300
    public void run() {
        for (int j = 0; j < 300; j++) {
            contador.incrementa();
        }
        System.out.println(getName() + " contador vale " + contador.getValor());
    }
}// FIN HILOA

class HiloB3 extends Thread {
    private Contador3 contador;

    //Asignamos un nombre al hilo y un valor para el contador
    public HiloB3(String n, Contador3 c) {
        setName(n);
        contador = c;
    }

    //Decrementa el valor del contador en 300
    public void run() {
        for (int j = 0; j < 300; j++) {
            contador.decrementa();
        }
        System.out.println(getName() + " contador vale " + contador.getValor());
    }
}// FIN HILOB

public class CompartirObjetoMetodosSincronizados {
    public static void main(String[] args) {
        Contador3 cont = new Contador3(100); //El contador comenzará en 100
        HiloA3 a = new HiloA3("HiloA", cont);//El hilo A suma 300 al contador
        HiloB3 b = new HiloB3("HiloB", cont);//El hilo B resta 300 al contador
        a.start();
        b.start();

        //Espero a que finalicen las hebras
        try{
            a.join();
        }catch (Exception e){}
        try{
            b.join();
        } catch (Exception e){}
        System.out.println("El valor final del contador es " + cont.getValor());
    }
}
