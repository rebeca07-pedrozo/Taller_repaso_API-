package com.app.db;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

class PostgreSQLManagerTest {

    @Test
    void testConexionExitosa() {
        PostgreSQLManager db = new PostgreSQLManager();
        try {
            boolean conectado = db.conectar("jdbc:postgresql://localhost:5432/mibd", "usuario", "clave");
            assertTrue(conectado);
        } catch (SQLException e) {
            fail("No se esperaba excepción en conexión exitosa");
        }
    }

    @Test
    void testConexionFallida() {
        PostgreSQLManager db = new PostgreSQLManager();
        Exception ex = assertThrows(SQLException.class, () -> {
            db.conectar("jdbc:postgresql://localhost:5432/bd_no_existente", "wronguser", "wrongpass");
        });
        assertTrue(ex.getMessage().contains("No se pudo conectar"));
    }

    @Test
    void testContarRegistrosSinConexion() {
        PostgreSQLManager db = new PostgreSQLManager();
        assertThrows(SQLException.class, () -> db.contarRegistros("usuarios"));
    }
}
