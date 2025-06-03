package logic;

import com.mongodb.MongoTimeoutException;
import com.mongodb.client.MongoDatabase;
import db.MongoConnection;
import org.bson.Document;

import java.util.logging.Level;
import java.util.logging.Logger;

public class RecoveryHandler {
    private static final Logger logger = Logger.getLogger("RecoveryHandler");
    private MongoDatabase database;

    public RecoveryHandler() {
        database = MongoConnection.getDatabase();
    }

    public void insertWithRecovery(Document doc) {
        int attempts = 0;
        while (attempts < 3) {
            try {
                database.getCollection("records").insertOne(doc);
                logger.info("Documento insertado con éxito");
                return;
            } catch (MongoTimeoutException e) {
                logger.log(Level.WARNING, "Error de conexión a MongoDB, reintentando...", e);
                attempts++;
                try {
                    Thread.sleep(2000);
                    MongoConnection.connect();
                    database = MongoConnection.getDatabase();
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
            }
        }
        logger.severe("No se pudo insertar el documento tras 3 intentos");
    }
}
