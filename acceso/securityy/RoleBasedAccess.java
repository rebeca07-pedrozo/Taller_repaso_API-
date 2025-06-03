package security;

import model.Rol;
import model.Usuario;

import java.util.Map;
import java.util.Set;

public class RoleBasedAccess {
    private static final Map<Rol, Set<String>> permisos = Map.of(
            Rol.ADMIN, Set.of("BORRAR", "ACTUALIZAR", "VER"),
            Rol.USUARIO, Set.of("VER")
    );

    public static boolean permite(Usuario usuario, String accion) {
        return permisos.getOrDefault(usuario.getRol(), Set.of()).contains(accion);
    }
}
