package cliente;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Cliente {
    private static final int MAX_REINTENTOS = 5;

    public static void main(String[] args) {
        int intento = 0;
        long backoff = 1000;

        while (intento < MAX_REINTENTOS) {
            try (Socket socket = new Socket("localhost", 1234);
                 BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                 Scanner scanner = new Scanner(System.in)) {

                System.out.println("Conectado al servidor.");

                String input;
                while (true) {
                    System.out.print("Ingrese operación (ej. suma 3 4): ");
                    input = scanner.nextLine();
                    if (input.equalsIgnoreCase("salir")) break;
                    out.println(input);
                    System.out.println("Respuesta: " + in.readLine());
                }
                break;

            } catch (IOException e) {
                intento++;
                System.out.println("Error de conexión. Reintentando en " + backoff + " ms...");
                try { Thread.sleep(backoff); } catch (InterruptedException ignored) {}
                backoff *= 2;
            }
        }
    }
}
