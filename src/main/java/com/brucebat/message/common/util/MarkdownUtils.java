package com.brucebat.message.common.util;


import org.apache.commons.lang3.StringUtils;

/**
 * Markdown工具类
 *
 * @version 1.0
 * @author : Sun Tianyu
 * @since  Created in 2020/7/21
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
        return stringBuilder.toString() + " " + title + " " + stringBuilder.toString() + " \n";
    }

    /**
     * 获取引用
     *
     * @param quote 获取引用
     * @return 处理完成结果
     */
    public static String getQuote(String quote){
        return "> " + quote + " \n";
    }

    /**
     * 加粗
     *
     * @param text 待处理文本
     * @return 将文本加粗
     */
    public static String getBold(String text) {
        return "**" + text + "**";
    }

    /**
     * 斜体
     *
     * @param text 待处理文本
     * @return 将文本置为斜体
     */
    public static String getItalic(String text) {
        return "*" + text + "*";
    }

    /**
     * 生成网页链接格式
     *
     * @param title 网页标题
     * @param link 网页链接
     * @return 处理完成格式
     */
    public static String getLink(String title, String link) {
        return "[" + title + "]" + "(" + link + ")";
    }

    /**
     * 生成图片链接格式
     *
     * @param title 标题
     * @param imageLink 图片链接
     * @return 处理完成格式
     */
    public static String getImageLink(String title, String imageLink) {
        return "![" + title + "]" + "(" + imageLink + ")";
    }

    /**
     * 生成指定格式无序列表
     * 注意：这里只会有一层无序结构
     *
     * @param texts 待处理无序列表
     * @return 处理完成格式
     */
    public static String getUnsortedList(String... texts) {
        if (null == texts){
            return null;
        }
        StringBuilder result = new StringBuilder();
        for (String text : texts){
            if (StringUtils.isBlank(text)) {
                continue;
            }
            result.append("- ").append(text).append(" \n");
        }
        return result.toString();
    }

}
