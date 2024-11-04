class Contador {
    private int c = 0; //Atributo contador
    Contador(int c) {
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

class HiloA extends Thread {
    private Contador contador;

    //Asignamos un nombre al hilo y un valor para el contador
    public HiloA(String n, Contador c) {
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

class HiloB extends Thread {
    private Contador contador;

    //Asignamos un nombre al hilo y un valor para el contador
    public HiloB(String n, Contador c) {
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
        Contador cont = new Contador(100); //El contador comenzará en 100
        HiloA a = new HiloA("HiloA", cont);//El hilo A suma 300 al contador
        HiloB b = new HiloB("HiloB", cont);//El hilo B resta 300 al contador
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
