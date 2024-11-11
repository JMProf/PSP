class Productor1 extends Thread {
    private Cola1 cola;
    private int n;

    public Productor1(Cola1 c, int n) {
        cola = c;
        this.n = n;
    }

    public void run() {
        for (int i = 0; i < 5; i++) {
            cola.put(i); //pone el número
            System.out.println(i + "=>Productor : " + n + ", produce: " + i);
            try {
                sleep(100);
            } catch (InterruptedException e) { }			
			
        }
    }
}

class Consumidor1 extends Thread {
    private Cola1 cola;
    private int n;

    public Consumidor1(Cola1 c, int n) {
        cola = c;
        this.n = n;
    }

    public void run() {
        int valor = 0;
        for (int i = 0; i < 5; i++) {
            valor = cola.get(); //recoge el número
            System.out.println(i + "=>Consumidor: " + n + ", consume: " + valor);
        }
    }
}

class Cola1 {
    private int numero;
    private boolean disponible = false;//inicialmente cola vacia

    public int get() {
        if(disponible) {      //hay número en la cola
            disponible = false; //se pone cola vacía
            return numero;      //se devuelve
        }
        else return -1;	//no hay número disponible, cola vacía	
    }

    public void put(int valor) {
        numero = valor;    //coloca valor en la cola 
        disponible = true; //disponible para consumir, cola llena	
    }
}

public class Produc_Consum1 {
  public static void main(String[] args) {  
    Cola1 cola = new Cola1();
	
    Productor1 p = new Productor1(cola, 1);	
    Consumidor1 c = new Consumidor1(cola, 1);
	
    p.start();
    c.start();

    // Esperar a que los hilos finalicen
    try {
      p.join();  // Espera a que el hilo 'p' termine
      c.join();  // Espera a que el hilo 'c' termine
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    System.out.println("Hilos Productor y Consumidor han finalizado.");

  }
}
