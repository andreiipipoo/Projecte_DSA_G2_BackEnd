package edu.upc.dsa.CRUD.util;


public class QueryHelper {


    // Creates an SQL INSERT query for the specified entity.
    public static String createQueryINSERT(Object entity) {
        // Initialize a StringBuffer to build the query.
        StringBuffer sb = new StringBuffer("INSERT INTO ").
                append(entity.getClass().getSimpleName()).
                append(" (");

        // Iterate over the fields of the entity and append them to the query.
        for (String f: ObjectHelper.getFields(entity)) {
            sb.append(f).append(", ");
        }
        sb.deleteCharAt(sb.length() - 1); // Remove the trailing comma and space
        sb.deleteCharAt(sb.length() - 1);

        // Append the VALUES part of the query with placeholders for each field.
        sb.append(") VALUES (");
        for (String f: ObjectHelper.getFields(entity)) {
            sb.append("?, ");
        }
        sb.deleteCharAt(sb.length() - 1); // Remove the trailing comma and space
        sb.deleteCharAt(sb.length() - 1);
        sb.append(")");

        // Return the final SQL INSERT query.
        return sb.toString();
    }


    // Creates an SQL UPDATE query for the specified entity.
    public static String createQueryUPDATE(Object entity) {
        // Initialize a StringBuffer to build the query.
        StringBuffer sb = new StringBuffer("");
        sb.append("UPDATE ").append(entity.getClass().getSimpleName()).
                append(" SET ");

        // Get the id and fields of the entity.
        String id = ("" + ObjectHelper.getter(entity, "id"));
        String[] fields = ObjectHelper.getFields(entity);

        // Append each field with a placeholder to the SET part of the query.
        for (String f: fields) {
            sb.append(f).append(" = ?,");
        }
        sb.deleteCharAt(sb.length() - 1); // Remove the trailing comma
        // Append the WHERE clause to filter by the entity's id.
        sb.append(" WHERE id = '").append(id).append("'");

        // Return the final SQL UPDATE query.
        return sb.toString();
    }


    // Creates an SQL SELECT query to retrieve a record by its id.
    public static String createQuerySELECTById(Class theClass, String id) {
        // Initialize a StringBuffer to build the query.
        StringBuffer sb = new StringBuffer("");
        sb.append("SELECT * FROM ").append(theClass.getSimpleName());
        // Append the WHERE clause to filter by the specified id.
        sb.append(" WHERE id = '").append(id).append("'");
        // Return the final SQL SELECT query.
        return sb.toString();
    }


    // Creates an SQL SELECT query to retrieve a record by its name.
    public static String createQuerySELECTByName(Class theClass, String username) {
        // Initialize a StringBuffer to build the query.
        StringBuffer sb = new StringBuffer("");
        sb.append("SELECT * FROM ").append(theClass.getSimpleName());
        // Append the WHERE clause to filter by the specified name.
        sb.append(" WHERE username = '").append(username).append("'");
        // Return the final SQL SELECT query.
        return sb.toString();
    }


    // Creates an SQL SELECT query to retrieve all records for the specified entity.
    public static String createQuerySELECTAll(Class theClass) {
        // Initialize a StringBuffer to build the query.
        StringBuffer sb = new StringBuffer("");
        sb.append("SELECT * FROM ").append(theClass.getSimpleName());
        // Return the final SQL SELECT query.
        return sb.toString();
    }


    // Creates an SQL DELETE query for the specified entity based on its id.
    public static String createQueryDELETE(Object entity){
        // Initialize a StringBuffer to build the query.
        StringBuffer sb = new StringBuffer("");
        // Get the id of the entity.
        String id = (ObjectHelper.getter(entity, "id").toString());
        // Append the DELETE statement with the WHERE clause to filter by the entity's id.
        sb.append("DELETE FROM ").append(entity.getClass().getSimpleName()).
                append(" WHERE id='").append(id).append("'");
        // Return the final SQL DELETE query.
        return sb.toString();
    }

}
