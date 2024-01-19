package edu.upc.dsa.CRUD.util;

import java.beans.IntrospectionException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.beans.PropertyDescriptor;


public class ObjectHelper {


    // Retrieves the names of all fields of the specified entity object.
    public static String[] getFields(Object entity){
        // Get the Class of the entity object.
        Class theClass = entity.getClass();
        // Retrieve all declared fields of the entity class.
        Field[] fields = theClass.getDeclaredFields();
        // Create an array to store the names of the fields.
        String[] sFields = new String[fields.length];
        int i = 0;
        // Iterate through the fields and store their names in the array.
        for(Field f : fields) sFields[i++] = f.getName();
        // Return the array containing field names.
        return sFields;
    }


    // Sets the value of the specified property in the object using reflection.
    public static void setter(Object object, String property, Object value) {
        PropertyDescriptor pd;
        try {
            // Create a PropertyDescriptor for the specified property of the object's class.
            pd = new PropertyDescriptor(property, object.getClass());
            // Invoke the write method of the property descriptor to set the property value.
            pd.getWriteMethod().invoke(object, value);
        }
        catch (IntrospectionException | InvocationTargetException | IllegalAccessException | IllegalArgumentException ex){
            // Handle potential exceptions by printing the stack trace.
            ex.printStackTrace();
        }
    }


    // Retrieves the value of the specified property from the object using reflection.
    public static Object getter(Object object, String property) {
        PropertyDescriptor pd;
        try {
            // Create a PropertyDescriptor for the specified property of the object's class.
            pd = new PropertyDescriptor(property, object.getClass());
            // Invoke the read method of the property descriptor to get the property value.
            Object res = pd.getReadMethod().invoke(object);
            // Return the retrieved value.
            return res;
        }
        catch (IntrospectionException | InvocationTargetException | IllegalAccessException | IllegalArgumentException ex){
            // Handle potential exceptions by printing the stack trace.
            ex.printStackTrace();
        }
        // Return null if an exception occurs during the process.
        return null;
    }

}