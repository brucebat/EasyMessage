package com.brucebat.message.common.config;

import com.brucebat.message.service.DingTalkService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @version 1.0
 * @author: Sun Tianyu
 * @since : Created in 2020/7/27
 * 钉钉服务自动装配类
 */
@Configuration
@EnableConfigurationProperties(DingTalkProperties.class)
public class DingTalkAutoConfig {

    @Bean
    @ConditionalOnMissingBean
    public DingTalkService dingTalkService(DingTalkProperties dingTalkProperties) {
        return new DingTalkService(dingTalkProperties);
    }
}
