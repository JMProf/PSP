class Productor2 extends Thread {
    private Cola2 cola;
    private int n;

    public Productor2(Cola2 c, int n) {
        cola = c;
        this.n = n;
    }

    public void run() {
        for (int i = 0; i < 5; i++) {
            cola.put(i); //pone el número
        
            try {
                sleep(100);
            } catch (InterruptedException e) { }			
			
        }
    }
}

class Consumidor2 extends Thread {
    private Cola2 cola;
    private int n;

    public Consumidor2(Cola2 c, int n) {
        cola = c;
        this.n = n;
    }

    public void run() {
        int valor = 0;
        for (int i = 0; i < 5; i++) {
            valor = cola.get(); //recoge el número
          
        }
    }
}

class Cola2 {
    private int numero;
    private boolean disponible = false;//inicialmente cola vacia

    public synchronized int get() {
    	  while (!disponible) {
    	    try {
    	          wait();
    	    } catch (InterruptedException e) { }
    	  }
    	  System.out.println("Se consume: " + numero);    	  
    	  disponible = false;
    	  notifyAll();
    	  return numero;
    	}

    public synchronized void put(int valor) {
    	  while (disponible){
    	    try {
    	          wait();
    	    } catch (InterruptedException e) { }
    	  }
    	  numero = valor;
    	  disponible = true;
    	  System.out.println("Se produce: " + numero);   
    	  notifyAll();
    }
}

public class Produc_Consum2 {
  public static void main(String[] args) {  
    Cola2 cola = new Cola2();
	
    Productor2 p = new Productor2(cola, 1);	
    Consumidor2 c = new Consumidor2(cola, 1);
	
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
