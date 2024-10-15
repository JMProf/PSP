class HiloSimple extends Thread {
    @Override
    public void run(){
        for(int i=0; i<10;i++){
            System.out.println("Dentro del hilo");
        }
    }
}

public class UsaHilo1 {
    public static void main(String[] args){
        HiloSimple hs = new HiloSimple();
        hs.start();
        for(int i=0; i<10;i++){
            System.out.println("Fuera del hilo");
        }
    }
}
