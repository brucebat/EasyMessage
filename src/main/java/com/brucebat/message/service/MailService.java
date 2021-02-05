package com.brucebat.message.service;

import com.brucebat.message.common.config.MailProperties;

/**
 * 邮件服务类
 *
 * @author Sun Tianyu
 * @version 1.0
 * @since Created in 2021/2/5
 */
public class MailService {

    /**
     * 邮件配置信息
     */
    private MailProperties mailProperties;

    public MailService(MailProperties mailProperties) {
        this.mailProperties = mailProperties;
    }


}
