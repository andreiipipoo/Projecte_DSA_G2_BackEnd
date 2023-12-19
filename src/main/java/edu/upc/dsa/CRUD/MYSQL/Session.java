package edu.upc.dsa.CRUD.MYSQL;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.HashMap;

public interface Session<E> {
    void save(Object entity) throws SQLException;
    void close();
    Object get(Class theClass, String field , Object value);
    void delete(Object object);
    List<Object> findAll(Class theClass);
    List<Object> findAll(Class theClass, HashMap<String,String> params) throws SQLException;
    List<Object> query(String query, Class theClass, HashMap params);
    List<Object> getList(Class theClass, String key, Object value);

    //void update(Object object) throws SQLException;
}