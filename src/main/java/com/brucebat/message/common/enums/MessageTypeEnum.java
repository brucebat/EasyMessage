package com.brucebat.message.common.enums;

/**
 * @version 1.0
 * @author: Sun Tianyu
 * @since: Created in 2020/7/21
 *  钉钉消息类型枚举类
 */
public enum MessageTypeEnum {
    /**
     * 文本类型
     */
    TEXT("text", "文本类型"),
    /**
     * markdown类型
     */
    MARKDOWN("markdown", "markdown类型"),
    /**
     * 链接类型
     */
    LINK("link", "link类型"),
    /**
     * 事件卡片类型
     */
    ACTION_CARD("actionCard", "事件卡片类型"),
    /**
     * feed流卡片类型
     */
    FEED_CARD("feedCard", "feed流卡片类型");

    /**
     * 类型
     */
    private String type;
    /**
     * 描述
     */
    private String description;

    MessageTypeEnum(String type, String description) {
        this.type = type;
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }
}
