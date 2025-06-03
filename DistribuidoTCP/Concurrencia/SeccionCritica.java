package concurrencia;

import java.util.concurrent.locks.ReentrantLock;

public class SeccionCritica {
    private static final ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        Runnable tarea = () -> {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + " ingresó a la sección crítica");
                Thread.sleep(2000);
            } catch (InterruptedException ignored) {
            } finally {
                System.out.println(Thread.currentThread().getName() + " salió de la sección crítica");
                lock.unlock();
            }
        };

        for (int i = 0; i < 3; i++) {
            new Thread(tarea, "Hilo-" + i).start();
        }
    }
}
