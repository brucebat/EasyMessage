package com.brucebat.message.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author brucebat
 * @version 1.0
 * @since Created at 2021/4/22 10:55 上午
 */
@ConfigurationProperties("ding.app")
@Data
public class DingAppProperties {

    /**
     * 应用key
     */
    private String appKey;
    /**
     * 应用secret
     */
    private String appSecret;
    /**
     * 应用id
     */
    private String agentId;
}
