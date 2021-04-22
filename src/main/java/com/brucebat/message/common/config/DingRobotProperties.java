package com.brucebat.message.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 钉钉配置信息类
 *
 * @version 1.0
 * @author : Sun Tianyu
 * @since : Created in 2020/7/23
 */
@ConfigurationProperties(prefix = "message.ding.talk.robot")
@Data
public class DingRobotProperties {

    /**
     * 钉钉机器人令牌
     */
    private String accessToken;
    /**
     * 是否加签
     */
    private boolean enableSign;
    /**
     * 签名
     */
    private String encryptKey;
}
