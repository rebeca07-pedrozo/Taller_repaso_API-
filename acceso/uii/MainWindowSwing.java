package ui;

import model.Usuario;
import security.RoleBasedAccess;

import javax.swing.*;

public class MainWindowSwing extends JFrame {
    public MainWindowSwing(Usuario usuario) {
        setTitle("Panel de Usuario - " + usuario.getUsername());
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        JButton btnBorrar = new JButton("Borrar Datos");
        btnBorrar.setBounds(100, 100, 200, 30);
        btnBorrar.setEnabled(RoleBasedAccess.permite(usuario, "BORRAR"));
        add(btnBorrar);

        setVisible(true);
    }
}
