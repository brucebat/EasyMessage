package com.brucebat.message.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 邮箱配置信息
 *
 * @author Sun Tianyu
 * @version 1.0
 * @since Created in 2021/2/5
 */
@ConfigurationProperties(prefix = "message.mail")
@Data
public class MailProperties {

    /**
     * 发送者邮箱
     */
    private String from;

    /**
     * 发送者邮箱密码/授权码
     */
    private String password;

    /**
     * 连接超时时间
     */
    private Integer connectTimeout;

    /**
     * 邮件发送超时时间
     */
    private Integer writeTimeout;

    /**
     * 邮件接收超时时间
     */
    private Integer receiveTimeout;

    /**
     * 是否开启debug模式
     */
    private Boolean debugMode;
}
