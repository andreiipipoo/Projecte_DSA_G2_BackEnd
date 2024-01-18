package edu.upc.dsa.CRUD.util;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.HashMap;
import java.util.Objects;
import java.lang.reflect.Field;

public class QueryHelper {

    public static String createQueryINSERT(Object entity) {
        StringBuffer sb = new StringBuffer("INSERT INTO ").
                append(entity.getClass().getSimpleName()).
                append(" (");

        for (String f : ObjectHelper.getFields(entity)) {
            sb.append(f).append(", ");
        }
        sb.deleteCharAt(sb.length() - 1); //comma and space
        sb.deleteCharAt(sb.length() - 1);

        sb.append(") VALUES (");
        for (String f : ObjectHelper.getFields(entity)) {
            sb.append("?, ");
        }
        sb.deleteCharAt(sb.length() - 1); //comma and space
        sb.deleteCharAt(sb.length() - 1);
        sb.append(")");

        return sb.toString();
    }

    public static String createQuerySELECTAll(Class theClass) {
        StringBuffer sb = new StringBuffer("");
        sb.append("SELECT * FROM ").append(theClass.getSimpleName());
        return sb.toString();
    }

    public static String createQueryDELETE(Object entity)  {
        StringBuffer sb = new StringBuffer("");
        String id = (ObjectHelper.getter(entity, "id").toString());
        sb.append("DELETE FROM ").append(entity.getClass().getSimpleName()).
                append(" WHERE id='").append(id).append("'");
        return sb.toString();
    }

    public static String createQuerySELECTByName(Class theClass, String name) {
        StringBuffer sb = new StringBuffer("");
        sb.append("SELECT * FROM ").append(theClass.getSimpleName());
        sb.append(" WHERE name = '").append(name).append("'");
        return sb.toString();
    }

    public static String createQuerySELECTById(Class theClass, String id) {
        StringBuffer sb = new StringBuffer("");
        sb.append("SELECT * FROM ").append(theClass.getSimpleName());
        sb.append(" WHERE id = '").append(id).append("'");
        return sb.toString();
    }

    public static String createQueryUPDATE(Object entity) {
        StringBuffer sb = new StringBuffer("UPDATE ");
        sb.append(entity.getClass().getSimpleName());
        sb.append(" SET ");
        String id = (ObjectHelper.getter(entity,"id").toString());
        String [] fields = ObjectHelper.getFields(entity);
        for(String field: fields){
            sb.append(field).append(" = ?,");
        }
        //This part is for deleting the last coma
        sb.deleteCharAt(sb.length() - 1);
        sb.append(" WHERE id = '").append(id).append("'");
        return sb.toString();
    }
}
