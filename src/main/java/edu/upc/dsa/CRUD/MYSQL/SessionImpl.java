package edu.upc.dsa.CRUD.MYSQL;

import edu.upc.dsa.CRUD.util.ObjectHelper;
import edu.upc.dsa.CRUD.util.QueryHelper;

import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;


public class SessionImpl implements Session {
    private final Connection conn;
    public SessionImpl(Connection conn) {
        this.conn = conn;
    }

    public void save(Object entity) throws SQLException{
        String insertQuery = QueryHelper.createQueryINSERT(entity);
        PreparedStatement pstm = null;
        try {
            pstm = conn.prepareStatement(insertQuery);
            pstm.setObject(1, 0);
            int i = 1;
            for (String field: ObjectHelper.getFields(entity)) {
                pstm.setObject(i++, ObjectHelper.getter(entity, field));
            }
            pstm.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void close(){
        try {
            this.conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Object get(Class theClass, String field, Object value) {
        String selectQuery = QueryHelper.createQuerySELECT(theClass, field);
        PreparedStatement pstm = null;
        ResultSet rs;
        boolean found = true;

        try{
            pstm = conn.prepareStatement(selectQuery);
            pstm.setObject(1, value);
            rs = pstm.executeQuery();

            ResultSetMetaData metadata = rs.getMetaData();
            int numberOfColumns = metadata.getColumnCount();
            Object o = theClass.newInstance();
            Object object = null;
            while (rs.next()){
                for (int j=1; j<=numberOfColumns; j++){
                    String columnName = metadata.getColumnName(j);
                    ObjectHelper.setter(o, columnName, rs.getObject(j));
                    o = rs.getObject(j);
                }
            }
            return o;
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /*public void update(Object object) throws SQLException {

    }*/

    public void delete(Object object) {
    }

    public List<Object> findAll(Class theClass) {

        String query = QueryHelper.createQuerySELECTAll(theClass);
        PreparedStatement pstm =null;
        ResultSet rs;
        List<Object> list = new LinkedList<>();
        try {
            pstm = conn.prepareStatement(query);
            pstm.executeQuery();
            rs = pstm.getResultSet();

            ResultSetMetaData metadata = rs.getMetaData();
            int numberOfColumns = metadata.getColumnCount();

            while (rs.next()){
                Object o = theClass.newInstance();
                for (int j=1; j<=numberOfColumns; j++){
                    String columnName = metadata.getColumnName(j);
                    ObjectHelper.setter(o, columnName, rs.getObject(j));
                }
                list.add(o);
            }
            return list;
        } catch (SQLException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Object> query(String query, Class theClass, HashMap params) {
       return null;
    }

    public List<Object> findAll(Class theClass, HashMap params){
        String query = QueryHelper.createQuerySELECTWithParams(theClass, params);
        PreparedStatement pstm =null;
        try{
            pstm = conn.prepareStatement(query);
            int i = 1;
            for (Object value: params.values()) {
                pstm.setObject(i++, value);
            }
            pstm.executeQuery();
            ResultSet rs = pstm.getResultSet();
            List<Object> list = new LinkedList<>();
            ResultSetMetaData metadata = rs.getMetaData();
            int numberOfColumns = metadata.getColumnCount();
            while (rs.next()){
                Object o = theClass.newInstance();
                for (int j=1; j<=numberOfColumns; j++){
                    String columnName = metadata.getColumnName(j);
                    ObjectHelper.setter(o, columnName, rs.getObject(j));
                }
                list.add(o);
            }
            return list;
        } catch (SQLException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Object> getList(Class theClass, String key, Object value) {
        String query = QueryHelper.createQuerySELECT(theClass, key);
        ResultSet rs;
        List<Object> list = new LinkedList<>();
        PreparedStatement pstm;

        try {
            pstm = conn.prepareStatement(query);
            pstm.setObject(1, value);
            rs = pstm.executeQuery();
            ResultSetMetaData metadata = rs.getMetaData();
            int numberOfColumns = metadata.getColumnCount();
            while (rs.next()){
                Object o = theClass.newInstance();
                for (int j=1; j<=numberOfColumns; j++){
                    String columnName = metadata.getColumnName(j);
                    ObjectHelper.setter(o, columnName, rs.getObject(j));
                }
                list.add(o);
            }
            return list;
        } catch (SQLException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
