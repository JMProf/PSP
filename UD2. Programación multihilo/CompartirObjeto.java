class Contador1 {
    private int c = 0; //Atributo contador
    Contador1(int c) {
        this.c = c;
    }

    public void incrementa() {
        c = c + 1;
    }

    public void decrementa() {
        c = c - 1;
    }

    public int getValor() {
        return c;
    }

}// CONTADOR

class HiloA1 extends Thread {
    private Contador1 contador;

    //Asignamos un nombre al hilo y un valor para el contador
    public HiloA1(String n, Contador1 c) {
        setName(n);
        contador = c;
    }

    //Incrementa el valor del contador en 300
    public void run() {
        for (int j = 0; j < 300; j++) {
            contador.incrementa();
            try {
                sleep(10);
            } catch (InterruptedException e) {}
        }
        System.out.println(getName() + " contador vale " + contador.getValor());
    }
}// FIN HILOA

class HiloB1 extends Thread {
    private Contador1 contador;

    //Asignamos un nombre al hilo y un valor para el contador
    public HiloB1(String n, Contador1 c) {
        setName(n);
        contador = c;
    }

    //Decrementa el valor del contador en 300
    public void run() {
        for (int j = 0; j < 300; j++) {
            contador.decrementa();
            try {
                sleep(10);
            } catch (InterruptedException e) {}
        }
        System.out.println(getName() + " contador vale " + contador.getValor());
    }
}// FIN HILOB

public class CompartirObjeto {
    public static void main(String[] args) {
        Contador1 cont = new Contador1(100); //El contador comenzará en 100
        HiloA1 a = new HiloA1("HiloA", cont);//El hilo A suma 300 al contador
        HiloB1 b = new HiloB1("HiloB", cont);//El hilo B resta 300 al contador
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
