package concurrencia;

import java.io.*;
import java.nio.file.*;

public class SimulacionBloqueoDistribuido {
    private static final String ARCHIVO_ESTADO = "bloqueo.txt";

    public static boolean solicitarAcceso(String nombre) throws IOException, InterruptedException {
        while (true) {
            synchronized (SimulacionBloqueoDistribuido.class) {
                if (!Files.exists(Path.of(ARCHIVO_ESTADO))) {
                    Files.write(Path.of(ARCHIVO_ESTADO), nombre.getBytes());
                    System.out.println(nombre + " obtuvo el recurso.");
                    return true;
                } else {
                    System.out.println(nombre + " espera el recurso...");
                }
            }
            Thread.sleep(1000);
        }
    }

    public static void liberarRecurso() throws IOException {
        Files.deleteIfExists(Path.of(ARCHIVO_ESTADO));
        System.out.println("Recurso liberado.");
    }

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                if (solicitarAcceso("Proceso-A")) {
                    Thread.sleep(5000);
                    liberarRecurso();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                if (solicitarAcceso("Proceso-B")) {
                    Thread.sleep(2000);
                    liberarRecurso();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}
