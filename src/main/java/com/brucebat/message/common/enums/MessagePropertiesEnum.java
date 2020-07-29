package com.brucebat.message.common.enums;

/**
 * @version 1.0
 * @author: Sun Tianyu
 * @date: Created in 2020/7/22
 * @description
 */
public enum MessagePropertiesEnum {
    /**
     * 消息类型
     */
    MSG_TYPE("msgtype"),
    /**
     * 标题
     */
    TITLE("title"),
    /**
     * 文本
     */
    TEXT("text"),
    /**
     * 内容
     */
    CONTENT("content"),
    /**
     * 图片url
     */
    PIC_URL("picUrl"),
    /**
     * 消息url
     */
    MESSAGE_URL("messageUrl"),
    /**
     * markdown字段
     */
    MARKDOWM("markdown"),
    /**
     * link参数
     */
    LINK("link"),
    /**
     * 联系参数
     */
    AT("at"),
    /**
     * 电话联系人
     */
    AT_MOBILES("atMobiles"),
    /**
     * 是否通知所有人
     */
    IS_AT_ALL("isAtAll");

    /**
     * 参数
     */
    private String value;

    MessagePropertiesEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
