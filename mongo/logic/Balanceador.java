package logic;

import dao.MongoDAO;
import dao.PostgresDAO;
import org.bson.Document;

import java.sql.SQLException;

public class Balanceador {
    private MongoDAO mongoDAO;
    private PostgresDAO postgresDAO;

    public Balanceador() throws SQLException {
        mongoDAO = new MongoDAO("records");
        postgresDAO = new PostgresDAO();
        postgresDAO.createTable();
    }

    public void store(String key, String value) throws SQLException {
        int numericKey;
        try {
            numericKey = Integer.parseInt(key);
        } catch (NumberFormatException e) {
            mongoDAO.insert(new Document("key", key).append("value", value));
            return;
        }

        if (numericKey % 2 == 0) {
            mongoDAO.insert(new Document("key", key).append("value", value));
            System.out.println("Guardado en Mongo");
        } else {
            postgresDAO.insert(key, value);
            System.out.println("Guardado en Postgres");
        }
    }
}
