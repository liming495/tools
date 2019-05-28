package com.githup.liming495.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 字符串工具类
 *
 * @author by guppy
 * @date 2019/5/27 16:17
 */
public abstract class StringUtils {
    private static final String ZERO = "0";

    private static final char[] DIGITS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

    /**
     * 获取十六进制的SHA1值
     *
     * @param data
     * @return
     */
    public static String sha1Hex(String data) {
        if (data == null) {
            throw new IllegalArgumentException("data must not be null");
        }

        byte[] bytes = digest("SHA1", data);

        return toHexString(bytes);
    }

    private static String toHexString(byte[] bytes) {
        int l = bytes.length;

        char[] out = new char[l << 1];

        for (int i = 0, j = 0; i < l; i++) {
            out[j++] = DIGITS[(0xF0 & bytes[i]) >>> 4];
            out[j++] = DIGITS[0x0F & bytes[i]];
        }

        return new String(out);
    }

    private static byte[] digest(String algorithm, String data) {
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance(algorithm);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        return digest.digest(data.getBytes());
    }

    /**
     * 在target前面加0，如果直到长度等于maxLength
     *
     * @param maxLength
     *            最大长度
     * @param target
     *            目标字符串
     * @return
     */
    public static String addZero2First(int maxLength, String target) {
        if (maxLength == target.length()) {
            return target;
        }
        StringBuilder proxy = new StringBuilder(target);
        for (int i = maxLength - target.toString().length(); i > 0; i--) {
            proxy.insert(0, ZERO);
        }
        return proxy.toString();
    }

    /**
     * 在target前面加0，如果直到长度等于maxLength
     *
     * @param maxLength
     *            最大长度
     * @param target
     *            目标字符串
     * @return
     */
    public static String addZero2First(int maxLength, Object target) {
        if (target == null) {
            throw new IllegalArgumentException("o need not null");
        }
        return addZero2First(maxLength, target.toString());
    }

    /**
     * 空字符转换
     * <p>
     * if o == null return default
     *
     * @param o
     * @param defaultStr
     * @return
     */
    public static String nvlStr(Object o, String defaultStr) {
        return o == null ? defaultStr : o.toString();
    }

    /**
     * 空字符转换
     * <p>
     * if o == null return ""
     *
     * @param o
     * @return
     */
    public static String nvlStr(Object o) {
        return nvlStr(o, "");
    }

    /**
     * 首字母小写,后续字符串保持原样
     *
     * @param str
     *            String 要修改的字符串
     * @return String 首字母小写的字符串
     */
    public static String capLower(String str) {
        if (str == null || "".equals(str)) {
            return "";
        }
        if (str.length() == 1) {
            return str.toLowerCase();
        }

        String temp = str.substring(0, 1).toLowerCase().concat(str.substring(1));

        return temp;

    }

    /**
     * 首字母大写
     *
     * @param str
     *            String 要修改的字符串
     * @return String 首字母大写的字符串
     */
    public static String capUpper(String str) {
        if (str == null || "".equals(str)) {
            return "";
        }
        if (str.length() == 1) {
            return str.toUpperCase();
        }

        String temp = str.substring(0, 1).toUpperCase().concat(str.substring(1));
        return temp;
    }

    /**
     * 检查字符是否为合法的邮件地址
     *
     * @param mail
     * @return
     */
    public static boolean chkMail(String mail) {
        int i;
        int len = mail.length();
        int aPos = mail.indexOf("@");
        int dPos = mail.indexOf(".");
        int aaPos = mail.indexOf("@@");
        int adPos = mail.indexOf("@.");
        int ddPos = mail.indexOf("..");
        int daPos = mail.indexOf(".@");
        String lastChar = mail.substring(len - 1, len);
        String chkStr = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890-_@.";
        if (aPos <= 0 || aPos == len - 1 || dPos <= 0 || dPos == len - 1 || adPos > 0 || daPos > 0 || lastChar.equals("@")
                || lastChar.equals(".") || aaPos > 0 || ddPos > 0) {
            return false;
        }
        if (mail.indexOf("@", aPos + 1) > 0) {
            return false;
        }
        while (aPos > dPos) {
            dPos = mail.indexOf(".", dPos + 1);
            if (dPos < 0) {
                return false;
            }
        }
        for (i = 0; i < len; i++) {
            if (chkStr.indexOf(mail.charAt(i)) < 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 把多个字符串连接起来
     *
     * @param os
     *            可变长度对象数组
     * @return 字符串
     */
    public static String join(Object... os) {
        if (os == null || os.length == 0) {
            return null;
        }
        StringBuilder s = new StringBuilder();
        for (Object str : os) {
            s.append(str);
        }
        return s.toString();
    }

    /**
     * added by morris 2000/10/20 Filter the specified string for characters
     * that are senstive to HTML interpreters, returning the string with these
     * characters replaced by the corresponding character entities.
     */
    public static String filterForHtml(String value) {
        if (value == null) {
            return null;
        }
        StringBuffer result = new StringBuffer();
        value = value.trim();
        for (int i = 0; i < value.length(); i++) {
            char ch = value.charAt(i);
            if (ch == '<') {
                result.append("&lt;");
            } else if (ch == '>') {
                result.append("&gt;");
            } else if (ch == '&') {
                result.append("&amp;");
            } else if (ch == '"') {
                result.append("&quot;");
            } else {
                result.append(ch);
            }
        }
        return result.toString();
    }

    /**
     * Filter the specified string for characters that are senstive to
     * JavaScript interpreters, returning the string with these characters
     * replaced by the corresponding character entities.
     */
    public static String filterForJS(String value) {
        if (value == null) {
            return null;
        }
        StringBuffer result = new StringBuffer();
        value = value.trim();
        for (int i = 0; i < value.length(); i++) {
            char ch = value.charAt(i);
            if (ch == '\'') {
                result.append("\\'");
            } else if (ch == '"') {
                result.append("\\\"");
            } else {
                result.append(ch);
            }
        }
        return result.toString();
    }

    /**
     * 9999999 -> 9,999,999
     */
    public static String formatNum(java.math.BigDecimal src) {
        return formatNum(src.toString());
    }

    // End of update
    /**
     * 9999999 -> 9,999,999
     */
    public static String formatNum(String input0) {
        if (input0 == null) {
            return null;
        }

        if (input0.trim().length() == 0) {
            return "";
        }
        String input = input0;
        boolean neg = false;
        if (input.startsWith("-")) {
            neg = true;
            input = input0.substring(1);
        }
        int point = input.indexOf(".") > 0 ? input.indexOf(".") : input.length();
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < point - 1; i++) {
            if ((point - i - 1) % 3 == 0) {
                result.append(input.charAt(i)).append(",");
            } else {
                result.append(input.charAt(i));
            }
        }
        result.append(input.substring(point - 1));

        return neg ? "-" + result.toString() : result.toString();

    }

    /**
     * 判断字符串是否为空
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        return str == null || str.equals("");
    }

    /**
     * 判断多个字符串全部为空 isEmpty(s,s1,s2,...)
     *
     * @param strs
     * @return
     */
    public static boolean isEmpty(String... strs) {
        if (strs == null || strs.length == 0) {
            return true;
        }
        for (String s : strs) {
            if (notEmpty(s)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断是否为字母（包括大小字母）和数字
     *
     * @param str
     * @return
     */
    public static boolean isEntNum(String str) {
        String all = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int i;
        for (i = 0; i < str.length(); i++) {
            if (all.indexOf(str.substring(i, i + 1)) < 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 比较两个字符串是否值相等
     *
     * @param o1
     * @param o2
     * @return
     */
    public static boolean isEqual(Object o1, Object o2) {
        if (o1 == null) {
            return o2 == null;
        } else {
            return o1.equals(o2);
        }
    }

    /**
     * 判断字符串可否转化成整数
     *
     * @param str
     * @return
     */
    public static boolean isInteger(String str) {
        if (str == null) {
            return false;
        }
        return str.length() > 0 && str.matches("\\d+");
    }

    /**
     * 判断字符串可否转化成数字
     *
     * @param str
     * @return
     */
    public static boolean isNumber(String str) {
        if (str == null) {
            return false;
        }
        return str.length() > 0 && str.matches("\\d*\\.{0,1}\\d*") && !".".equals(str);
    }

    public static boolean isSpace(char c) {
        return c == ' ' || c == '\t';
    }

    public static boolean isSpace(String s) {
        if (s == null) {
            return false;
        }
        for (int i = 0; i < s.length(); i++) {
            if (!isSpace(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断字符串是否相似
     *
     * @param s
     * @return
     */
    public static boolean like(String s, String d, int tolerance) {
        if (notEmpty(s, d)) {
            int succeed = 0;
            char[] sb = s.toLowerCase().toCharArray();
            char[] db = d.toLowerCase().toCharArray();
            int length = sb.length;
            if (db.length > length) {
                length = db.length;
            }

            for (int i = 0; i < length; i++) {
                if (sb[i] == db[i]) {
                    succeed++;
                }
                if (i + 1 >= sb.length || i + 1 >= db.length) {
                    break;
                }
            }
            return length - succeed <= tolerance;
        }
        return true;

    }

    /**
     * 判断字符串不为空
     *
     * @param str
     * @return
     */
    public static boolean notEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * 判断多个字符串全部不为空 notEmpty(s,s1,s2,...)
     *
     * @param strs
     * @return
     */
    public static boolean notEmpty(String... strs) {
        if (strs == null || strs.length == 0) {
            return false;
        }
        for (String s : strs) {
            if (isEmpty(s)) {
                return false;
            }
        }
        return true;
    }

    public static String nullToSpace(String s) {
        return s == null ? "" : s;
    }

    /**
     * 空转化为空字串
     */
    public static String nvl(String sIn) {
        return sIn == null ? "" : sIn;
    }

    /**
     * 把空值转换为指定的字符串
     *
     * @param sIn
     *            待转换的字符串
     * @param sDef
     *            如果为空，转换后的字符串
     * @return 转换后的字符串
     */
    public static String nvl(String sIn, String sDef) {
        return sIn == null || "".equals(sIn) ? sDef : sIn;
    }

    /**
     * remove char from the input string
     *
     * @param str
     *            input string rc removed char
     * @return
     */
    public static String removeChar(String str, String rc) {
        if (str == null) {
            return null;
        }
        int i = str.indexOf(rc);
        while (i >= 0) {
            str = str.substring(0, i) + str.substring(i + 1, str.length());
            i = str.indexOf(rc);
        }
        return str;
    }

    /**
     *
     * @param src
     * @param sFnd
     * @param sRep
     */
    public static String replaceStr(String src, String sFnd, String sRep) {
        String sTemp = "";
        int endIndex = 0;
        int beginIndex = 0;
        do {
            endIndex = src.indexOf(sFnd, beginIndex);
            // logger.debug(endIndex + ":" + beginIndex);
            if (endIndex >= 0) {// mean found it.
                // logger.debug( src.substring(beginIndex,endIndex));
                sTemp += src.substring(beginIndex, endIndex) + sRep;
                beginIndex = endIndex + sFnd.length();
            } else if (beginIndex >= 0) {
                sTemp += src.substring(beginIndex);
                break;
            }
        } while (endIndex >= 0);
        // logger.debug(sTemp);
        return sTemp;
    }

    /**
     * convert a string value to BigDecimal value
     */
    public static java.math.BigDecimal toBigDecimal(String src) {
        if (src == null) {
            return new java.math.BigDecimal(0);
        } else {
            return new java.math.BigDecimal(src);
        }
    }

    /**
     * convert input value to html out string
     */
    public static String toHTMLOutStr(String sIn) {
        if (sIn == null) {
            return sIn;
        }
        String sOut = sIn;
        sOut = replaceStr(sOut, "<", "&lt;");
        sOut = replaceStr(sOut, ">", "&gt;");
        return sOut;
    }

    /**
     * convert input value to html return string
     */
    public static String toHTMLRtnStr(String sIn) {
        if (sIn == null) {
            return sIn;
        }
        String sOut = sIn;
        sOut = replaceStr(sOut, "\r\n", "<br>");
        return sOut;
    }

    public static String trim(String s) {
        if (s == null) {
            return null;
        }
        int begin, end;
        for (begin = 0; begin < s.length() && isSpace(s.charAt(begin)); begin++) {
            ;
        }
        for (end = s.length() - 1; end >= 0 && isSpace(s.charAt(end)); end--) {
            ;
        }
        if (end < begin) {
            return "";
        }
        return s.substring(begin, end + 1);
    }

    /*
     * Convert a string like "243,434,343" to long like 243434343
     */
    public static java.math.BigDecimal unFormatNum(String input) {
        if (input == null || input.equals("")) {
            return new java.math.BigDecimal(0);
        }
        try {
            return new java.math.BigDecimal(removeChar(input, ","));
        } catch (Exception e) {
            return new java.math.BigDecimal(-1);
        }
    }

    public String safeTrim(String src) {
        if (src != null) {
            return src.trim();
        } else {
            return null;
        }
    }


    /**
     * 裁剪数据，超过长度便裁剪
     *
     * @param o
     * @param maxLength
     * @return
     */
    public static  String cut(Object o, int maxLength) {
        if (o == null) {
            return null;
        }
        String s = o.toString();
        if (s.length() > maxLength) {
            return s.substring(0, maxLength);
        }
        return s;
    }
}
