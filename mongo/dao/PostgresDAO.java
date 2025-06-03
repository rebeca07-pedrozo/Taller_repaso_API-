package dao;

import db.PostgresConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PostgresDAO {
    private Connection conn;

    public PostgresDAO() throws SQLException {
        conn = PostgresConnection.getConnection();
    }

    public void createTable() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS records (key VARCHAR(100) PRIMARY KEY, value VARCHAR(100))";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.execute();
        }
    }

    public void insert(String key, String value) throws SQLException {
        String sql = "INSERT INTO records (key, value) VALUES (?, ?) ON CONFLICT (key) DO UPDATE SET value = EXCLUDED.value";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, key);
            stmt.setString(2, value);
            stmt.executeUpdate();
        }
    }
}
