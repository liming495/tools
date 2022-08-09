package com.githup.liming495.utils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * 代码工具类，用于生成一些有规则的代码
 *
 * @author Guppy
 */
public abstract class CodeUtils {
    /**
     * 根据属性文件生成调用代码
     *
     * @param pFile
     *            属性文件，需放在类路径下
     * @param classname 要生成的类名
     * @param annotation 生成后的类注解
     */
    public static void generatePropertiesClass(String pFile, String classname, String annotation) {
        Properties properties = new Properties();
        try {
            properties.load(CodeUtils.class.getClassLoader().getResourceAsStream(pFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("import java.io.IOException;");
        System.out.println("import java.util.Properties;");
        System.out.println("/**" + annotation);
        System.out.println("* @author AKuo,Auto-generated");
        System.out.println("* @version 1.0");
        System.out.println("* @date " + new Date());
        System.out.println("*/");
        System.out.println("public class " + classname + " {");
        System.out.println("static {");
        System.out.println("init();");
        System.out.println("}");

        for (Map.Entry<Object, Object> e : properties.entrySet()) {

            System.out.println("/**  */");
            System.out.println("public static String " + e.getKey().toString().replaceAll("\\.", "_") + ";");
        }

        System.out.println("public static void init() {");
        System.out.println("Properties p = new Properties();");
        System.out.println("try {");
        System.out.println("p.load(" + classname + ".class.getClassLoader().getResourceAsStream(\"" + pFile + "\"));");
        for (Map.Entry<Object, Object> e : properties.entrySet()) {
            System.out.println(e.getKey().toString().replaceAll("\\.", "_") + "=p.getProperty(\"" + e.getKey() + "\");");
        }
        System.out.println("} catch (IOException e) {e.printStackTrace();}p.clear();p = null;}}");

    }

    /**
     * 生成toString方法，把里面的属性分别组成一组字符串，并打印到控制台
     *
     * @param sc
     *            目标类名
     * @param recursion
     *            是否递归，即把父类也包括进去。
     * @param ignores
     *            忽略的字段数组
     *
     *
     * @return .append("[fieldName-").append(getFieldName()||fieldName)
     */
    @SuppressWarnings("rawtypes")
    public static String generateToString(Class sc, boolean recursion, String[] ignores) {
        StringBuilder builder = new StringBuilder();
        while (sc != null) {
            builder.append(generateToString(sc, ignores));
            if (!recursion) {
                break;
            }
            sc = sc.getSuperclass();
        }
        System.out.println("return new StringBuilder()" + builder + ".toString();");
        return builder.toString();

    }

    @SuppressWarnings("rawtypes")
    private static String generateToString(Class sc, String[] ignores) {
        Field[] fs_s = sc.getDeclaredFields();
        StringBuilder builder = new StringBuilder();
        if (fs_s.length < 1) {
            return "";
        }
        builder.append(".append(\"[");
        for (Field fd : fs_s) {
            boolean ignore = false;
            if (ignores != null && ignores.length > 0) {
                for (String ignorestr : ignores) {
                    if (StringUtils.like(fd.getName(), ignorestr, 0)) {
                        ignore = true;
                    }
                }
            }
            if (!ignore) {
                builder.append(fd.getName());
                builder.append("-\").append(");
                String getMethodName = getGetMethodName(sc, fd);
                builder.append(getMethodName != null ? getMethodName + "()" : fd.getName());
                builder.append(").append(\"][");
            }

        }
        builder.deleteCharAt(builder.lastIndexOf("["));
        builder.append("\")");
        return builder.toString();
    }

    @SuppressWarnings("rawtypes")
    private static String getGetMethodName(Class sc, Field fieldName) {
        Method[] ms = sc.getDeclaredMethods();
        for (Method m : ms) {
            if (StringUtils.like(m.getName(), "get" + fieldName.getName(), 0)) {
                return m.getName();
            }
        }
        return null;
    }

    /**
     * <p>
     * 创建GET PUT
     * 用于生成两个类成员名称类似(不区分大小写)的赋值操作
     * 如果类型不匹配
     * 则会生成ObjectUtils.object2Type
     * 并最终是存放在MAP里面
     * map.put("s.fieldname",s.getField())
     * </p>
     *
     * @param sc 有数据的类
     * @param dc 准备放数据的类
     * @param s 有数据的对象名称
     * @param map 准备放数据的对象名称
     * @param tolerance 容许每个成员名称中字符不匹配的数量，0则完全匹配
     * @param nullable 标识
     */
    @SuppressWarnings("rawtypes")
    public static void generateGetPut(Class sc, Class dc, String s, String map, int tolerance, boolean nullable) {
        List<String> fails = new ArrayList<String>();
        List<String> noset = new ArrayList<String>();
        List<String> noget = new ArrayList<String>();
        Field[] fs_s = sc.getDeclaredFields();
        Field[] fs_d = dc.getDeclaredFields();
        Method[] ms_s = sc.getDeclaredMethods();
        Method[] ms_d = dc.getDeclaredMethods();
        int count = 0;
        int count_equals = 0;
        int count_sm = 0;
        int count_dm = 0;
        for (Field fd : fs_d) {
            String method_set = "";
            for (Method m : ms_d) {
                if (StringUtils.like(m.getName(), "set" + fd.getName(), 0)) {
                    method_set = m.getName();
                    count_dm++;
                    break;
                }
            }
            if ("".equals(method_set)) {
                noset.add(fd.getName());
            }
            boolean flag = false;

            for (Field fs : fs_s) {

                if (StringUtils.like(fs.getName(), fd.getName(), tolerance)) {
                    flag = true;
                    count_equals++;
                    String method_get = "";

                    for (Method m : ms_s) {
                        if (StringUtils.like(m.getName(), "get" + fs.getName(), 0)) {
                            method_get = m.getName();
                            count_sm++;
                            break;
                        }
                    }
                    if ("".equals(method_get)) {
                        noget.add(fs.getName());
                    }

                    if (fd.getType() == fs.getType()) {
                        if (!nullable) {
                            System.out.println("if(!ObjectUtils.isEmpty(" + s + "." + method_get + "()))");
                        }
                        System.out.println(map + ".put(\"" + fd.getName() + "\"," + s + "." + method_get + "());");
                        count++;
                    } else {
                        if (!nullable) {
                            System.out.println("if(!ObjectUtils.isEmpty(" + s + "." + method_get + "()))");
                        }
                        System.out.println(map + ".put(\"" + fd.getName() + "\"," + "ObjectUtils.object2"
                                + StringUtils.capUpper(fd.getType().getSimpleName()) + "(" + s + "." + method_get + "()));");
                        count++;
                    }

                }
            }
            if (!flag) {
                fails.add(fd.getName());
            }
        }

        System.out.println("/*===========================================");

        System.out.println(dc.getSimpleName() + "类中找到变量：" + fs_d.length);
        System.out.println(dc.getSimpleName() + "类中找到set方法：" + count_dm);
        if (noset.size() > 0) {
            System.out.print(dc.getSimpleName() + "类中缺少的set方法:");
            for (String fail : noset) {
                System.out.print(fail + ",");
            }
            System.out.println();
        }

        System.out.println(sc.getSimpleName() + "类中找到相同的变量：：" + count_equals);
        if (fails.size() > 0) {
            System.out.print(sc.getSimpleName() + "类中没有找到的变量:");
            for (String fail : fails) {
                System.out.print(fail + ",");
            }
            System.out.println();
        }

        System.out.println(sc.getSimpleName() + "类中找到对应的get方法：" + count_sm);

        if (noget.size() > 0) {
            System.out.print(sc.getSimpleName() + "类中没有找到的get方法:");
            for (String fail : noget) {
                System.out.print(fail + ",");
            }
            System.out.println();
        }

        System.out.println("共输出打印数量：" + count);
        System.out.println("===========================================*/");

    }

    /**
     * <p>
     * 创建GET SET，用于生成两个类成员名称类似(不区分大小写)的赋值操作，如果类型不匹配，则会生成ObjectUtils.object2Type
     * d.setField(s.getField());
     * </p>
     *
     *
     * @param sc
     *            有数据的类
     * @param dc
     *            准备放数据的类
     * @param s
     *            有数据的对象名称
     * @param d
     *            准备放数据的对象名称
     * @param tolerance
     *            容许每个成员名称中字符不匹配的数量，0则完全匹配
     * @param nullable
     *            如果不允许为空，那就会生成判断语句 if(o!=null)
     */
    @SuppressWarnings("rawtypes")
    public static void generateGetSet(Class sc, Class dc, String s, String d, int tolerance, boolean nullable) {
        List<String> fails = new ArrayList<String>();
        List<String> noset = new ArrayList<String>();
        List<String> noget = new ArrayList<String>();
        Field[] fs_s = sc.getDeclaredFields();
        Field[] fs_d = dc.getDeclaredFields();
        Method[] ms_s = sc.getDeclaredMethods();
        Method[] ms_d = dc.getDeclaredMethods();
        int count = 0;
        int count_equals = 0;
        int count_sm = 0;
        int count_dm = 0;
        for (Field fd : fs_d) {
            String method_set = "";
            for (Method m : ms_d) {
                if (StringUtils.like(m.getName(), "set" + fd.getName(), 0)) {
                    method_set = m.getName();
                    count_dm++;
                    break;
                }
            }
            if ("".equals(method_set)) {
                noset.add(fd.getName());
            }
            boolean flag = false;

            for (Field fs : fs_s) {

                if (StringUtils.like(fs.getName(), fd.getName(), tolerance)) {
                    flag = true;
                    count_equals++;
                    String method_get = "";

                    for (Method m : ms_s) {
                        if (StringUtils.like(m.getName(), "get" + fs.getName(), 0)) {
                            method_get = m.getName();
                            count_sm++;
                            break;
                        }
                    }
                    if ("".equals(method_get)) {
                        noget.add(fs.getName());
                    }

                    if (fd.getType() == fs.getType()) {
                        if (!nullable) {
                            System.out.println("if(!ObjectUtils.isEmpty(" + s + "." + method_get + "()))");
                        }
                        System.out.println(d + "." + method_set + "(" + s + "." + method_get + "());");
                        // System.out.print(s + "." + method_get + "(),");
                        count++;
                    } else {
                        if (!nullable) {
                            System.out.println("if(!ObjectUtils.isEmpty(" + s + "." + method_get + "()))");
                        }
                        System.out.println(d + "." + method_set + "(ObjectUtils.object2"
                                + StringUtils.capUpper(fd.getType().getSimpleName()) + "(" + s + "." + method_get + "()));");
                        count++;
                    }

                }
            }
            if (!flag) {
                fails.add(fd.getName());
            }
        }

        System.out.println("/*===========================================");
        System.out.println(dc.getSimpleName() + "类中找到变量：" + fs_d.length);
        System.out.println(dc.getSimpleName() + "类中找到set方法：" + count_dm);
        if (noset.size() > 0) {
            System.out.print(dc.getSimpleName() + "类中缺少的set方法:");
            for (String fail : noset) {
                System.out.print(fail + ",");
            }
            System.out.println();
        }

        System.out.println(sc.getSimpleName() + "类中找到相同的变量：：" + count_equals);
        if (fails.size() > 0) {
            System.out.print(sc.getSimpleName() + "类中没有找到的变量:");
            for (String fail : fails) {
                System.out.print(fail + ",");
            }
            System.out.println();
        }

        System.out.println(sc.getSimpleName() + "类中找到对应的get方法：" + count_sm);

        if (noget.size() > 0) {
            System.out.print(sc.getSimpleName() + "类中没有找到的get方法:");
            for (String fail : noget) {
                System.out.print(fail + ",");
            }
            System.out.println();
        }

        System.out.println("共输出打印数量：" + count);
        System.out.println("===========================================*/");
    }
}
