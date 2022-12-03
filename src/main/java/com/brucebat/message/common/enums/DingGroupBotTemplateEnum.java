package com.brucebat.message.common.enums;

/**
 * 钉钉群组机器人模板枚举类
 *
 * @author brucebat
 * @version 1.0
 * @since Created at 2022/12/3 17:37
 */
public enum DingGroupBotTemplateEnum {
    /**
     * 官方text模板
     */
    INNER_APP_TEMPLATE_TEXT("inner_app_template_text", "官方text模板"),
    /**
     * 官方照片模板
     */
    INNER_APP_TEMPLATE_PHOTO("inner_app_template_photo", "官方照片模板"),
    /**
     * 官方markdown模板
     */
    INNER_APP_TEMPLATE_MARKDOWN("inner_app_template_markdown", "官方markdown模板"),
    /**
     * 官方卡片模板
     */
    INNER_APP_TEMPLATE_ACTION_CARD("inner_app_template_action_card", "官方卡片模板");

    private final String templateId;
    private final String desc;

    DingGroupBotTemplateEnum(String templateId, String desc) {
        this.templateId = templateId;
        this.desc = desc;
    }

    public String getTemplateId() {
        return templateId;
    }

    public String getDesc() {
        return desc;
    }
}
