package db;

import com.datastax.oss.driver.api.core.CqlSession;

import java.net.InetSocketAddress;

public class CassandraConnection {
    private static CqlSession session;

    public static void connect() {
        session = CqlSession.builder()
                .addContactPoint(new InetSocketAddress("127.0.0.1", 9042))
                .withLocalDatacenter("datacenter1")
                .build();
        System.out.println("Conectado a Cassandra");
    }

    public static CqlSession getSession() {
        if (session == null) connect();
        return session;
    }

    public static void close() {
        if (session != null) session.close();
    }
}
