package com.githup.liming495.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * JSON工具类
 *
 * @author by guppy
 * @since 2019/5/27 13:01
 */
public abstract class JSONUtils {
    private static final ObjectMapper MAPPER = new ObjectMapper();
    public static final String JSON_EMPTY = "{}";
    public static final String DEFAULT_FAIL = "\"Parse failed\"";
    private static final com.fasterxml.jackson.databind.ObjectMapper objectMapper = new com.fasterxml.jackson.databind.ObjectMapper();
    private static final ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();

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
     * @param <T>  T
     * @return JAVA对象
     * @throws IOException IOException
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
     * @param <T>          T
     * @return 对象
     * @throws IOException IOException
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

    public static void marshal(File file, Object value) throws Exception {
        try {
            objectWriter.writeValue(file, value);
        } catch (IOException e) {
            throw new Exception(e);
        }
    }

    public static void marshal(OutputStream os, Object value) throws Exception {
        try {
            objectWriter.writeValue(os, value);
        } catch (IOException e) {
            throw new Exception(e);
        }
    }

    public static String marshal(Object value) {
        try {
            return objectWriter.writeValueAsString(value);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return JSON_EMPTY;
    }

    public static byte[] object2Bytes(Object value) throws Exception {
        try {
            return objectWriter.writeValueAsBytes(value);
        } catch (IOException e) {
            throw new Exception(e);
        }
    }

    public static <T> T file2Object(File file, Class<T> valueType) throws Exception {
        try {
            return objectMapper.readValue(file, valueType);
        } catch (IOException e) {
            throw new Exception(e);
        }
    }

    public static <T> T stream2Object(InputStream is, Class<T> valueType) throws Exception {
        try {
            return objectMapper.readValue(is, valueType);
        } catch (IOException e) {
            throw new Exception(e);
        }
    }

    /**
     * List<Map<String, String>> map2 =
     * JSONUtils.json2TypeReference(json, new TypeReference<List<Map<String, String>>>() {});
     */
    public static <T> T json2TypeReference(String jsonString, TypeReference<T> typeReference) {
        try {
            return objectMapper.readValue(jsonString, typeReference);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> T json2Object(String str, Class<T> valueType) {
        try {
            return objectMapper.readValue(str, valueType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> T bytes2Object(byte[] bytes, Class<T> valueType) throws Exception {
        try {
            if (bytes == null) {
                bytes = new byte[0];
            }
            return objectMapper.readValue(bytes, 0, bytes.length, valueType);
        } catch (IOException e) {
            throw new Exception(e);
        }
    }
}