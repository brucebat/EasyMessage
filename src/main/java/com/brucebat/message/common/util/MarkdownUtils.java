package com.brucebat.message.common.util;

/**
 * @version 1.0
 * @author: Sun Tianyu
 * @date: Created in 2020/7/21
 * @description
 */
public class MarkdownUtils {

    /**
     * 最高标题级别
     */
    private static final int MAX_TITLE_LEVEL = 6;

    /**
     * 最低标题级别
     */
    private static final int MIN_TITLE_LEVEL = 1;

    /**
     * 根据级别获取生成指定级别markdown格式标题,这里进行限定，最少1级，最多6级
     *
     * @param level 标题级别
     * @param title 标题
     * @return markdown格式标题
     */
    public static String getTitle(int level, String title) {
        if (level < MIN_TITLE_LEVEL) {
            level = MIN_TITLE_LEVEL;
        }
        if (level > MAX_TITLE_LEVEL) {
            level = MAX_TITLE_LEVEL;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < level; i++) {
            stringBuilder.append("#");
        }
        return stringBuilder.toString() + title + stringBuilder.toString();
    }
}
