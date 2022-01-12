package com.brucebat.message.common.enums;

import java.util.Arrays;

/**
 * 邮件类型枚举类
 *
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
    private final String type;
    /**
     * 描述
     */
    private final String desc;

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

    /**
     * 根据类型查找邮件类型枚举类
     *
     * @param type 类型
     * @return 邮件类型枚举类
     */
    public static MailTypeEnum find(String type) {
        return Arrays.stream(MailTypeEnum.values()).filter(p -> p.getType().equals(type)).findFirst().orElse(null);
    }
}
