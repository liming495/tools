package com.githup.liming495.utils;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;

import java.io.IOException;

/**
 * JSON工具类
 *
 * @author by guppy
 * @since 2019/5/27 13:01
 */
public abstract class JSONUtils {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static final String JSON_EMPTY = "{}";

    /**
     * Java对象转JSON
     *
     * @param o
     *            Java对象
     * @return String JSON
     */
    public static String Object2Json(Object o) {
        try {
            return MAPPER.writeValueAsString(o);
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return JSON_EMPTY;
    }

    /**
     * JSON字符串转Java对象
     *
     * @param json
     *            源字符串
     * @param c
     *            类
     * @return JAVA对象
     */
    public static Object Json2Object(String json, Class<?> c) {

        try {
            return MAPPER.readValue(json, c);
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return JSON_EMPTY;
    }

    /**
     * JSON字符串转Java LIST对象
     *
     * @param json
     * @param listClass
     *            List类
     * @param elementClass
     *            泛型的类
     * @return
     */
    public static Object Json2List(String json, Class<?> listClass, Class<?> elementClass) {

        try {
            return MAPPER.readValue(json, getCollectionType(listClass, elementClass));
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return JSON_EMPTY;
    }

    /**
     * 对泛型进行支持
     *
     * @param collectionClass
     * @param elementClasses
     * @return
     */
    public static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
        return MAPPER.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }
}