package com.brucebat.message.common.enums;

/**
 * 钉钉返回结果成员属性枚举类
 * 
 * @author brucebat
 * @version 1.0
 * @since Created at 2021/4/22 3:14 下午
 */
public enum DingResponseEnum {
    /**
     * 调用token
     */
    ACCESS_TOKEN("access_token", "调用token"),
    /**
     * 错误码
     */
    ERROR_CODE("errcode", "错误码"),
    /**
     * 错误信息
     */
    ERROR_MSG("errmsg", "错误信息"),
    /**
     * 结果
     */
    RESULT("result", "结果"),
    /**
     * 任务id
     */
    TASK_ID("task_id", "任务id");

    /**
     * 返回结果成员属性
     */
    private String filed;
    /**
     * 返回结果成员属性描述
     */
    private String desc;

    DingResponseEnum(String filed, String desc) {
        this.filed = filed;
        this.desc = desc;
    }

    public String getFiled() {
        return filed;
    }

    public String getDesc() {
        return desc;
    }
}
