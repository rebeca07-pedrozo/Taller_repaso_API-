package concurrencia;

import java.util.concurrent.BlockingQueue;

public class Consumidor implements Runnable {
    private final BlockingQueue<String> cola;

    public Consumidor(BlockingQueue<String> cola) {
        this.cola = cola;
    }

    @Override
    public void run() {
        try {
            while (true) {
                String tarea = cola.take();
                System.out.println("Consumidor: procesando " + tarea);
                Thread.sleep(1500);
            }
        } catch (InterruptedException e) {
            System.out.println("Consumidor interrumpido.");
        }
    }
}
