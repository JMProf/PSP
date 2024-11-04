class Contador2 {
    private int c = 0; //Atributo contador
    Contador2(int c) {
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

class HiloA2 extends Thread {
    private Contador2 contador;

    //Asignamos un nombre al hilo y un valor para el contador
    public HiloA2(String n, Contador2 c) {
        setName(n);
        contador = c;
    }

    //Incrementa el valor del contador en 300
    public void run() {

        //Se sincroniza el objeto contador
        synchronized(contador){
            for (int j = 0; j < 300; j++) {
                contador.incrementa();
            }
            System.out.println(getName() + " contador vale " + contador.getValor());
        }
    }
}// FIN HILOA

class HiloB2 extends Thread {
    private Contador2 contador;

    //Asignamos un nombre al hilo y un valor para el contador
    public HiloB2(String n, Contador2 c) {
        setName(n);
        contador = c;
    }

    //Decrementa el valor del contador en 300
    public void run() {

        //Se sincroniza el objeto contador
        synchronized(contador){
            for (int j = 0; j < 300; j++) {
                contador.decrementa();
            }
            System.out.println(getName() + " contador vale " + contador.getValor());
        }
    }
}// FIN HILOB

public class CompartirObjetoBloquesSincronizados {
    public static void main(String[] args) {
        Contador2 cont = new Contador2(100); //El contador comenzará en 100
        HiloA2 a = new HiloA2("HiloA", cont);//El hilo A suma 300 al contador
        HiloB2 b = new HiloB2("HiloB", cont);//El hilo B resta 300 al contador
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
