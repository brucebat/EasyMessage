package com.brucebat.message.common.config;

import com.brucebat.message.service.DingTalkService;
import com.brucebat.message.service.MailService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 钉钉服务自动装配类
 *
 * @version 1.0
 * @author : Sun Tianyu
 * @since : Created in 2020/7/27
 *
 */
@Configuration
@EnableConfigurationProperties({DingTalkProperties.class, MailProperties.class})
public class MessageAutoConfig {

    @Bean
    @ConditionalOnMissingBean(DingTalkService.class)
    public DingTalkService dingTalkService(DingTalkProperties dingTalkProperties) {
        return new DingTalkService(dingTalkProperties);
    }

    @Bean
    @ConditionalOnMissingBean(MailService.class)
    public MailService mailService(MailProperties mailProperties) {
        return new MailService(mailProperties);
    }
}
