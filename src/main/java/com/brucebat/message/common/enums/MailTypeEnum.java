package com.brucebat.message.common.enums;

/**
 * @author Sun Tianyu
 * @version 1.0
 * @since Created in 2021/2/4
 */
public enum MailTypeEnum {
    /**
     * 富文本
     */
    HTML("html", "富文本"),
    /**
     * 文本
     */
    TEXT("text", "文本");

    /**
     * 类型
     */
    private String type;
    /**
     * 描述
     */
    private String desc;

    MailTypeEnum(String type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public String getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }
}
