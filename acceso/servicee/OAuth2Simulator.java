package service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class OAuth2Simulator {
    private static final String CLIENT_ID = "cliente123";
    private static final String CLIENT_SECRET = "secreto456";
    private static final String SECRET = "clave.secreta.jwt.123";

    public static String obtenerToken(String clientId, String clientSecret) {
        if (CLIENT_ID.equals(clientId) && CLIENT_SECRET.equals(clientSecret)) {
            return Jwts.builder()
                    .setSubject("system-client")
                    .claim("rol", "SYSTEM")
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + 3600000))
                    .signWith(SignatureAlgorithm.HS256, SECRET)
                    .compact();
        }
        return null;
    }
}
