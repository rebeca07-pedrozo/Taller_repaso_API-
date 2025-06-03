package service;

import io.jsonwebtoken.*;
import model.Usuario;

import java.util.Date;

public class TokenService {
    private static final String SECRET = "clave.secreta.jwt.123";

    public static String generarToken(Usuario usuario) {
        return Jwts.builder()
                .setSubject(usuario.getUsername())
                .claim("rol", usuario.getRol().name())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600000))
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
    }

    public static Jws<Claims> validarToken(String token) {
        return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
    }
}
