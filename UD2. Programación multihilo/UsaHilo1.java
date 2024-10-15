class HiloSimple extends Thread {
    @Override
    public void run(){
        for(int i=0; i<10;i++){
            System.out.println("Dentro del hilo");
        }
    }
}
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class UsaHilo {
    public static void main(String[] args){
        HiloSimple hs = new HiloSimple();
        hs.start();
        for(int i=0; i<10;i++){
            System.out.println("Fuera del hilo");
        }
    }
}
