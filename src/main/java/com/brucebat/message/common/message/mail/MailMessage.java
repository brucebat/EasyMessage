package com.brucebat.message.common.message.mail;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 邮件消息
 *
 * @author Sun Tianyu
 * @version 1.0
 * @since Created in 2021/2/4
 */
@Data
public class MailMessage implements Serializable {

    /**
     * 邮件标题
     */
    private String title;
    /**
     * 邮件内容
     */
    private String content;
    /**
     * 邮件类型
     * @see com.brucebat.message.common.enums.MailTypeEnum
     */
    private String mailType;
    /**
     * 收件人邮箱地址
     */
    private List<String> toAddress;
    /**
     * 抄送人邮箱地址，选填
     */
    private List<String> ccAddress;
    /**
     * 密送人地址，选填
     */
    private List<String> bccAddress;

}
