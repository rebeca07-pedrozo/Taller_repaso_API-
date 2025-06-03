package ui;

import model.Usuario;
import service.AuthService;

import javax.swing.*;

public class LoginSwing {
    private final AuthService authService;

    public LoginSwing(AuthService authService) {
        this.authService = authService;
    }

    public Usuario mostrarLogin() {
        JTextField usuarioField = new JTextField();
        JPasswordField claveField = new JPasswordField();

        Object[] fields = {
                "Usuario:", usuarioField,
                "Contraseña:", claveField
        };

        int option = JOptionPane.showConfirmDialog(null, fields, "Login", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String usuario = usuarioField.getText();
            String clave = new String(claveField.getPassword());
            Usuario u = authService.login(usuario, clave);
            if (u != null) return u;
            JOptionPane.showMessageDialog(null, "Login fallido.");
        }
        return null;
    }
}
