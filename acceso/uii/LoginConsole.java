package ui;

import model.Usuario;
import service.AuthService;

import java.util.Scanner;

public class LoginConsole {
    private final AuthService authService;

    public LoginConsole(AuthService authService) {
        this.authService = authService;
    }

    public Usuario iniciarSesion() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Usuario: ");
        String user = sc.nextLine();
        System.out.print("Contraseña: ");
        String pass = sc.nextLine();

        Usuario u = authService.login(user, pass);
        if (u != null) {
            System.out.println("¡Bienvenido, " + u.getUsername() + "!");
            return u;
        } else {
            System.out.println("Login fallido.");
            return null;
        }
    }
}
