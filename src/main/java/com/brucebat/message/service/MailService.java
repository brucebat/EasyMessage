package com.brucebat.message.service;

import com.brucebat.message.common.config.MailProperties;
import com.brucebat.message.common.exception.MessageException;
import com.brucebat.message.common.message.mail.MailMessage;
import com.brucebat.message.common.util.MailUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

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
    private final MailProperties mailProperties;

    public MailService(MailProperties mailProperties) {
        this.mailProperties = mailProperties;
    }

    /**
     * 根据本地配置的邮箱信息进行邮件发送
     *
     * @param message 待发送邮件信息
     * @return 是否发送成功
     * @throws MessageException 消息发送异常
     */
    public boolean sendMail(MailMessage message) throws MessageException {
        if (Objects.isNull(mailProperties)) {
            throw new MessageException("sw-0001", "配置异常 : 发送邮件所需信息未进行配置");
        }
        return sendMail(message, mailProperties.getFrom(), mailProperties.getPassword());
    }

    /**
     * 根据发送者和密码/授权码进行邮件发送
     *
     * @param message 邮件信息
     * @param from 发送者
     * @param password 发送者邮箱密码/授权码
     * @return 是否发送成功
     * @throws MessageException 消息推送异常
     */
    public boolean sendMail(MailMessage message, String from, String password) throws MessageException {
        this.validate(message, from, password);
        // 进行邮件发送会话初始化
        // 组装请求参数
        // 进行实际邮件发送
        return false;
    }

    /**
     * 进行请求参数校验
     *
     * @param message 消息内容
     * @param from 发送者
     * @param password 发送者邮箱密码/授权码
     */
    private void validate(MailMessage message, String from, String password) throws MessageException {
        if (StringUtils.isBlank(from) || StringUtils.isBlank(password)) {
            throw new MessageException("sw-0001", "请求参数异常 : 发送者邮箱信息未设置");
        }
        if (!MailUtil.checkAddress(from)) {
            throw new MessageException("sw-0001", "请求参数异常 : 邮箱地址");
        }
        if (Objects.isNull(message) || StringUtils.isBlank(message.getContent()) || StringUtils.isBlank(message.getTitle())) {
            throw new MessageException("sw-0001", "请求参数异常 : 邮件内容不能为空");
        }
        if (CollectionUtils.isEmpty(message.getTo())) {
            throw new MessageException("sw-0001", "请求参数异常 : 邮件接收者不能为空");
        }
    }

}
