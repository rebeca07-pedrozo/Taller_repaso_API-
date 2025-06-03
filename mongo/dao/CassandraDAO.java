package dao;

import com.datastax.oss.driver.api.core.cql.SimpleStatement;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.CqlSession;
import db.CassandraConnection;

public class CassandraDAO {
    private CqlSession session;
    private String keyspace;
    private String table;

    public CassandraDAO(String keyspace, String table) {
        this.keyspace = keyspace;
        this.table = table;
        session = CassandraConnection.getSession();
        createKeyspace();
        createTable();
    }

    private void createKeyspace() {
        String query = "CREATE KEYSPACE IF NOT EXISTS " + keyspace + " WITH replication = {'class':'SimpleStrategy', 'replication_factor':1}";
        session.execute(query);
    }

    private void createTable() {
        String query = "CREATE TABLE IF NOT EXISTS " + keyspace + "." + table + " (key text PRIMARY KEY, value text)";
        session.execute(query);
    }

    public void insert(String key, String value) {
        String query = "INSERT INTO " + keyspace + "." + table + " (key, value) VALUES (?, ?)";
        session.execute(SimpleStatement.newInstance(query, key, value));
    }
}
