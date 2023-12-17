package edu.upc.dsa.CRUD.util;

import java.util.HashMap;
import java.util.Objects;

public class QueryHelper {

    public static String createQueryINSERT(Object entity){
        StringBuffer sb = new StringBuffer("INSERT INTO ");
        sb.append(entity.getClass().getSimpleName()).append(" ");
        sb.append("(");

        String [] fields = ObjectHelper.getFields(entity);

        for (String field: fields) {
            sb.append(field).append(", ");
        }

        sb=sb.replace(sb.length()-2,sb.length(),"");
        sb.append(") VALUES (?");

        for (String field: fields) {
            sb.append(", ?");
        }
        sb=sb.replace(sb.length()-3,sb.length(),"");
        sb.append(")");

        return sb.toString();

        /*
         StringBuilder sb = new StringBuilder("INSERT INTO ");
         sb.append(entity.getClass().getSimpleName()).append(" (");

         String[] fields = ObjectHelper.getFields(entity);

        // Join fields using commas with Java 8's StringJoiner
        StringJoiner joiner = new StringJoiner(", ");
        for (String field : fields) {
        joiner.add(field);
        }
        sb.append(joiner.toString());

        sb.append(") VALUES (");

        // Append placeholders for each field
        for (int i = 0; i < fields.length; i++) {
        sb.append("?");
        if (i < fields.length - 1) {
            sb.append(", ");
        }
        }
        sb.append(")");
        return sb.toString();
         */
    }

    public static String createQuerySELECT(Class theClass, String field, String value){
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT * FROM ").append(theClass.getSimpleName());
        sb.append(" WHERE " + field + " = ?");
        return sb.toString();
        /*
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT * FROM ").append(entity.getClass().getSimpleName());
        sb.append(" WHERE ID = ?");
        return sb.toString();
         */
    }

    public static String createQuerySELECTAll(Class theClass){
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT * FROM ").append(theClass.getSimpleName());
        return sb.toString();
    }

    public static String createQueryDELETE(Class theClass, String field, String value){
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(theClass.getSimpleName());
        sb.append(" WHERE " + field + " = ?");
        return sb.toString();
    }

    public static String createQueryUPDATE(Object entity, String field, String value){
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE Player ");
        sb.append(" SET " + field + " = ?");
        sb.append(" WHERE USERNAME = ?");
        return sb.toString();
    }
}
