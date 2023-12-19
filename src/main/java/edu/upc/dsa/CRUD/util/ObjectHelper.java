package edu.upc.dsa.CRUD.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

public class ObjectHelper {
    public static String[] getFields(Object entity){
        Class theClass = entity.getClass();
        Field[] fields = theClass.getDeclaredFields();
        String[] sFields = new String[fields.length];
        int i = 0;
        for(Field f : fields) sFields[i++] = f.getName();
        return sFields;
    }
    public static void setter(Object object, String property, Object value) {
        // Method // invoke
        List<Method> methods = new ArrayList<>(Arrays.asList(object.getClass().getDeclaredMethods()));
        try {
            Method m = methods.stream().filter((Method method) -> method.getName().contains("set" + getMethodName(property))).findFirst().get();
            m.invoke(object,  value);
        }catch ( IllegalAccessException e) {
            //logger.warn("No setter found for: " + property + " in " + object.getClass().getName());
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getMethodName(String property) {
        return property.substring(0,1).toUpperCase()+property.substring(1);
    }

    public static String getFieldName(Class theClass, String fields){
        Field field = (Field) Arrays.stream(theClass.getDeclaredFields()).filter((x) -> {
            return x.getName().matches("(?i).*"+ fields +".*");
        }).findFirst().orElse((Field) null);

        assert field != null;
        return field.getName();
    }

    public static Object getter(Object object, String property) {
        // Method // invoke
        String propToUppercase = property.substring(0, 1).toUpperCase() + property.substring(1);
        String getterName = "get" + propToUppercase;
        try {
            Method method = object.getClass().getDeclaredMethod(getterName);
            Object value = method.invoke(object);
            return value;
        }catch (NoSuchMethodException  | IllegalAccessException | InvocationTargetException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    //getIdFieldName
}