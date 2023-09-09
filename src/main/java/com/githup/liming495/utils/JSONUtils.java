package com.githup.liming495.utils;

import org.codehaus.jackson.JsonParser;
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
     * @param o Java对象
     * @return String JSON
     */
    public static String Object2Json(Object o) {
        try {
            return MAPPER.writeValueAsString(o);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return JSON_EMPTY;
    }

    /**
     * JSON字符串转Java对象
     *
     * @param json 源字符串
     * @param c    类
     * @return JAVA对象
     */
    public static <T> T Json2Object(String json, Class<T> c) throws IOException {
        MAPPER.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        try {
            return MAPPER.readValue(json, c);
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * JSON字符串转Java LIST对象
     *
     * @param json         字符串
     * @param listClass    List类
     * @param elementClass 泛型的类
     * @return 对象
     */
    public static <T> T Json2List(String json, Class<T> listClass, Class<T> elementClass) throws IOException {

        try {
            return MAPPER.readValue(json, getCollectionType(listClass, elementClass));
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 对泛型进行支持
     *
     * @param collectionClass 集合类型
     * @param elementClasses  元素类型
     * @return 类型
     */
    public static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
        return MAPPER.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }
}