package com.brucebat.message.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @version 1.0
 * @author: Sun Tianyu
 * @date: Created in 2020/7/23
 * @description
 */
@ConfigurationProperties(prefix = "dingtalk.robot")
public class DingTalkProperties {

    /**
     * 通知地址
     */
    private String notifyUrl;
    /**
     * 是否加签
     */
    private boolean signEnable;
    /**
     * 签名
     */
    private String signature;

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public boolean isSignEnable() {
        return signEnable;
    }

    public void setSignEnable(boolean signEnable) {
        this.signEnable = signEnable;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}
