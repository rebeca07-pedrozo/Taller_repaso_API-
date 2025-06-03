package dao;

import java.sql.SQLException;

public interface GenericDAO<T> {
    void create() throws SQLException;
    void insert(T item) throws SQLException;
    void update(T item) throws SQLException;
    void delete(T item) throws SQLException;
    T findByKey(String key) throws SQLException;
}
