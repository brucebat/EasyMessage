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
     */
    private String mailType;
    /**
     * 收件人
     */
    private List<String> to;

}
