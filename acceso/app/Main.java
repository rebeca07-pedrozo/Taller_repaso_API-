
package app;

import dao.UsuarioDAOImpl;
import model.Usuario;
import service.AuthService;
import service.AuthServiceImpl;
import ui.LoginSwing;
import ui.MainWindowSwing;

import java.sql.Connection;
import java.sql.DriverManager;

public class Main {
    public static void main(String[] args) throws Exception {
        Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/tu_basedatos", "usuario", "clave");
        AuthService authService = new AuthServiceImpl(new UsuarioDAOImpl(conn));

        LoginSwing loginSwing = new LoginSwing(authService);
        Usuario u = loginSwing.mostrarLogin();

        if (u != null) {
            new MainWindowSwing(u);
        } else {
            System.exit(0);
        }
    }
}
