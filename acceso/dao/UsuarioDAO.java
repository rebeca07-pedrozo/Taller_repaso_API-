package dao;

import model.Usuario;

public interface UsuarioDAO {
    Usuario obtenerUsuarioPorUsername(String username);
}
