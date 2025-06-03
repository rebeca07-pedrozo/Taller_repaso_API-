package dao;

import model.Usuario;
import model.Rol;
import util.PasswordUtils;

import java.sql.*;

public class UsuarioDAOImpl implements UsuarioDAO {
    private Connection conexion;

    public UsuarioDAOImpl(Connection conexion) {
        this.conexion = conexion;
    }

    @Override
    public Usuario obtenerUsuarioPorUsername(String username) {
        try {
            String sql = "SELECT username, password, rol FROM usuarios WHERE username = ?";
            PreparedStatement stmt = conexion.prepareStatement(sql);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String user = rs.getString("username");
                String pass = rs.getString("password");
                Rol rol = Rol.valueOf(rs.getString("rol").toUpperCase());
                return new Usuario(user, pass, rol);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
