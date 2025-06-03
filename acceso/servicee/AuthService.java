package service;

import model.Usuario;

public interface AuthService {
    Usuario login(String username, String password);
}
