package com.brucebat.message.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 钉钉配置信息类
 *
 * @version 1.0
 * @author : Sun Tianyu
 * @since : Created in 2020/7/23
 */
@ConfigurationProperties(prefix = "ding.talk.robot")
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
