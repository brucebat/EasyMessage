package com.brucebat.message.common.config;

import com.brucebat.message.service.DingAppMessageService;
import com.brucebat.message.service.DingAppService;
import com.brucebat.message.service.DingRobotService;
import com.brucebat.message.service.MailService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 钉钉服务自动装配类
 *
 * @author : Sun Tianyu
 * @version 1.0
 * @since : Created in 2020/7/27
 */
@Configuration
@EnableConfigurationProperties({DingRobotProperties.class, MailProperties.class, DingAppProperties.class})
public class MessageAutoConfig {

    /**
     * 创建钉钉机器人实例并托管给Spring容器
     *
     * @param dingRobotProperties 钉钉机器人配置信息
     * @return 钉钉机器人bean
     */
    @Bean
    @ConditionalOnMissingBean(DingRobotService.class)
    public DingRobotService dingRobotService(DingRobotProperties dingRobotProperties) {
        return new DingRobotService(dingRobotProperties);
    }

    /**
     * 创建邮件服务实例并托管给Spring容器
     *
     * @param mailProperties 邮件配置信息
     * @return 邮件服务实例
     */
    @Bean
    @ConditionalOnMissingBean(MailService.class)
    public MailService mailService(MailProperties mailProperties) {
        return new MailService(mailProperties);
    }

    /**
     * 创建钉钉应用服务并托管给Spring容器
     *
     * @param dingAppProperties 钉钉应用配置信息
     * @return 钉钉应用服务
     */
    @Bean
    @ConditionalOnMissingBean(DingAppService.class)
    public DingAppService dingAppService(DingAppProperties dingAppProperties) {
        return new DingAppService(dingAppProperties);
    }

    /**
     * 创建钉钉应用消息服务类
     *
     * @return 钉钉应用消息服务
     */
    @Bean
    @ConditionalOnMissingBean(DingAppMessageService.class)
    public DingAppMessageService dingAppMessageService() {
        return new DingAppMessageService();
    }
}
