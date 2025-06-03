package Concurrencia;

import java.util.concurrent.*;

public class RecursoCompartido {
    public static void main(String[] args) {
        BlockingQueue<String> cola = new LinkedBlockingQueue<>();
        new Thread(new Productor(cola)).start();
        new Thread(new Consumidor(cola)).start();
    }
}
