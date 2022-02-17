package com.githup.liming495.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符表情工具类
 *
 * @author Guppy
 */
public abstract class EmojiUtils {
    private static final Pattern emoji = Pattern.compile(
            "[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]", Pattern.UNICODE_CASE
                    | Pattern.CASE_INSENSITIVE);

    /**
     * 检查是否有EMOJI表情
     *
     * @param content 字符串
     * @return 结果
     */
    public static boolean has(String content) {
        Matcher emojiMatcher = emoji.matcher(content);
        return emojiMatcher.find();
    }
}
