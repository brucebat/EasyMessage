package com.brucebat.message.common.enums;

/**
 * 钉钉API接口枚举类
 *
 * @author brucebat
 * @version 1.0
 * @since Created at 2021/4/22 11:17 上午
 */
public enum DingApiEnum {
    /**
     * 获取accessToken
     */
    GET_ACCESS_TOKEN("获取accessToken", "https://oapi.dingtalk.com/gettoken?appkey=%s&appsecret=%s"),
    /**
     * 根据手机号获取用户id
     */
    GET_USER_ID_BY_PHONE("根据手机号获取用户id", "https://oapi.dingtalk.com/topapi/v2/user/getbymobile?access_token=%s"),
    /**
     * 发送通知消息
     */
    SEND_WORK_NOTICE("发送通知消息", "https://oapi.dingtalk.com/topapi/message/corpconversation/asyncsend_v2?access_token=%s"),
    /**
     * 发送模板通知消息
     */
    SEND_TEMPLATE_WORK_NOTICE("发送模板通知消息", "https://oapi.dingtalk.com/topapi/message/corpconversation/sendbytemplate?access_token=%s"),
    /**
     * 根据群模板创建群
     */
    CREATE_CONVERSATION("根据群模板创建群", "https://oapi.dingtalk.com/topapi/im/chat/scenegroup/create?access_token=%s"),

    /**
     * 进行群组机器人消息发送
     */
    CONVERSATION_GROUP_ROBOT_SEND("群组机器人消息发送", "https://oapi.dingtalk.com/topapi/im/chat/scencegroup/message/send_v2");

    /**
     * api描述
     */
    private String apiDesc;
    /**
     * api路径
     */
    private String apiPath;

    DingApiEnum(String apiDesc, String apiPath) {
        this.apiDesc = apiDesc;
        this.apiPath = apiPath;
    }

    public String getApiDesc() {
        return apiDesc;
    }

    public String getApiPath() {
        return apiPath;
    }
}
