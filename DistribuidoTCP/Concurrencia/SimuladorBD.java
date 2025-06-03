package concurrencia;

import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimuladorBD {
    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(5);

        Runnable tarea = () -> {
            try (FileWriter fw = new FileWriter("bd_simulada.txt", true)) {
                fw.write(Thread.currentThread().getName() + " escribió a la BD\n");
                fw.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        };

        for (int i = 0; i < 10; i++) {
            pool.execute(tarea);
        }

        pool.shutdown();
    }
}
