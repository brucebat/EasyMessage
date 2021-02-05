package com.brucebat.message.service;

import com.brucebat.message.common.config.MailProperties;

/**
 * @author Sun Tianyu
 * @version 1.0
 * @since Created in 2021/2/5
 */
public class MailService {

    private MailProperties mailProperties;

    public MailService(MailProperties mailProperties) {
        this.mailProperties = mailProperties;
    }


}
