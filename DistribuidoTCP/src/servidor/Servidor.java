package servidor;

import java.io.*;
import java.net.*;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Logger;

import log.LoggerConfig;

public class Servidor {
    private static final int PUERTO = 1234;
    private static final Logger logger = LoggerConfig.getLogger();
    public static final ReentrantLock lockLog = new ReentrantLock();

    public static void main(String[] args) {
        try (ServerSocket servidor = new ServerSocket(PUERTO)) {
            logger.info("Servidor iniciado en el puerto " + PUERTO);

            while (true) {
                Socket cliente = servidor.accept();
                logger.info("Nuevo cliente conectado: " + cliente.getInetAddress());
                new Thread(new ManejadorCliente(cliente)).start();
            }

        } catch (IOException e) {
            logger.severe("Error en el servidor: " + e.getMessage());
        }
    }
}
