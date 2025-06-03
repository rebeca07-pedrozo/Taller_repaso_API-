package db;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoConnection {
    private static final String URI = "mongodb://localhost:27017";
    private static MongoClient mongoClient;
    private static MongoDatabase database;

    public static void connect() {
        mongoClient = MongoClients.create(URI);
        database = mongoClient.getDatabase("testdb");
        System.out.println("Conectado a MongoDB");
    }

    public static MongoDatabase getDatabase() {
        if (database == null) connect();
        return database;
    }

    public static void close() {
        if (mongoClient != null) mongoClient.close();
    }
}
