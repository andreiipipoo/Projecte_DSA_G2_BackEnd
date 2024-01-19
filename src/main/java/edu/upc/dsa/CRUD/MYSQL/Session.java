package edu.upc.dsa.CRUD.MYSQL;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.HashMap;
import edu.upc.dsa.models.*;

public interface Session<E> {
    void save(Object entity);
    void delete(Object entity);
    Object getByName(Class theClass, String username);
    Object getById(Class theClass, String id);
    List<E> getAll(Class theClass);
    void close();
    void update(Object entity);
}