package edu.upc.dsa.CRUD.MYSQL;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

public interface Session<E> {
    void save(Object entity) throws SQLIntegrityConstraintViolationException;
    void close();
    Object get(Class theClass, String field, String value) throws SQLException;
    void update(String field, String player, String value) throws SQLIntegrityConstraintViolationException;
    void delete(Class theClass, String field, String value);
    List<Object> findAll(Class theClass);
    public int size(Class theClass);
}