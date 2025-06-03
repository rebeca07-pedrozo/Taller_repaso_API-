package service;

import dao.UsuarioDAO;
import model.Usuario;
import util.PasswordUtils;
import util.LogUtils;
import util.SessionManager;

public class AuthServiceImpl implements AuthService {
    private UsuarioDAO usuarioDAO;

    public AuthServiceImpl(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    @Override
    public Usuario login(String username, String password) {
        if (SessionManager.isBlocked(username)) {
            LogUtils.logAccess(username, false, "Usuario bloqueado temporalmente.");
            return null;
        }

        Usuario user = usuarioDAO.obtenerUsuarioPorUsername(username);
        if (user != null && PasswordUtils.verifyPassword(password, user.getPassword())) {
            if (SessionManager.isUserActive(username)) {
                LogUtils.logAccess(username, false, "Sesión ya activa.");
                return null;
            }
            SessionManager.activateUser(username);
            SessionManager.resetFailures(username);
            LogUtils.logAccess(username, true, "Login exitoso.");
            return user;
        } else {
            SessionManager.recordFailure(username);
            LogUtils.logAccess(username, false, "Credenciales inválidas.");
            return null;
        }
    }
}
