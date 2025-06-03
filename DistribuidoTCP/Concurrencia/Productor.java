package concurrencia;

import java.util.concurrent.BlockingQueue;

public class Productor implements Runnable {
    private final BlockingQueue<String> cola;

    public Productor(BlockingQueue<String> cola) {
        this.cola = cola;
    }

    @Override
    public void run() {
        int i = 1;
        try {
            while (true) {
                String tarea = "Tarea-" + i++;
                cola.put(tarea);
                System.out.println("Productor: agregó " + tarea);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            System.out.println("Productor interrumpido.");
        }
    }
}
