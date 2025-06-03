package dao;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import db.MongoConnection;

public class MongoDAO {
    private MongoCollection<Document> collection;

    public MongoDAO(String collectionName) {
        MongoDatabase db = MongoConnection.getDatabase();
        collection = db.getCollection(collectionName);
    }

    public void insert(Document doc) {
        collection.insertOne(doc);
    }
}
