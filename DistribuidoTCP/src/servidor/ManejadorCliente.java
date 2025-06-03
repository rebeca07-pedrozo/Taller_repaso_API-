package servidor;

import java.io.*;
import java.net.*;
import java.util.logging.Logger;

import log.LoggerConfig;

public class ManejadorCliente implements Runnable {
    private Socket socket;
    private static final Logger logger = LoggerConfig.getLogger();

    public ManejadorCliente(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
        ) {
            String entrada;
            while ((entrada = in.readLine()) != null) {
                String respuesta = procesarOperacion(entrada);
                out.println(respuesta);

                synchronized (Servidor.lockLog) {
                    logger.info("Cliente: " + socket.getInetAddress() + " -> " + entrada + " = " + respuesta);
                }
            }
        } catch (IOException e) {
            logger.warning("Error con cliente: " + e.getMessage());
        }
    }

    private String procesarOperacion(String input) {
        try {
            String[] partes = input.split(" ");
            String op = partes[0];
            int a = Integer.parseInt(partes[1]);
            int b = Integer.parseInt(partes[2]);

            return switch (op) {
                case "suma" -> String.valueOf(a + b);
                case "resta" -> String.valueOf(a - b);
                case "mult" -> String.valueOf(a * b);
                case "div" -> b != 0 ? String.valueOf(a / b) : "Error: división por cero";
                default -> "Operación inválida";
            };
        } catch (Exception e) {
            return "Error en el formato";
        }
    }
}
