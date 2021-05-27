package com.brucebat.message.service.mail;

import com.brucebat.message.common.config.MailProperties;
import com.brucebat.message.common.enums.MailPropertiesEnum;
import com.brucebat.message.common.enums.MailTypeEnum;
import com.brucebat.message.common.exception.MessageException;
import com.brucebat.message.common.message.mail.MailMessage;
import com.brucebat.message.common.util.MailUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * 邮件服务类
 *
 * @author Sun Tianyu
 * @version 1.0
 * @since Created in 2021/2/5
 */
@Slf4j
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
     * @param mailMessage 待发送邮件信息
     * @return 是否发送成功
     * @throws MessageException 消息发送异常
     */
    public boolean sendMail(MailMessage mailMessage) throws MessageException {
        if (Objects.isNull(mailProperties)) {
            throw new MessageException("sw-0001", "配置异常 : 发送邮件所需信息未进行配置");
        }
        return sendMail(mailMessage, mailProperties.getFrom(), mailProperties.getPassword());
    }

    /**
     * 根据发送者和密码/授权码进行邮件发送
     *
     * @param mailMessage 邮件信息
     * @param from 发送者
     * @param password 发送者邮箱密码/授权码
     * @return 是否发送成功
     * @throws MessageException 消息推送异常
     */
    public boolean sendMail(MailMessage mailMessage, String from, String password) throws MessageException {
        this.validate(mailMessage, from, password);
        try {
            Session session = this.initSession(from, password);
            Message message = this.buildMessage(mailMessage, session, from);
            // 进行实际邮件发送
            this.doSend(message, mailMessage.getMailType(), mailMessage.getContent());
            return true;
        } catch (Exception e) {
            log.error("send mail error, execption is {}", e);
        }
        return false;
    }

    /**
     * 进行请求参数校验
     *
     * @param mailMessage 消息内容
     * @param from 发送者
     * @param password 发送者邮箱密码/授权码
     * @throws MessageException 消息异常
     */
    private void validate(MailMessage mailMessage, String from, String password) throws MessageException {
        if (StringUtils.isBlank(from) || StringUtils.isBlank(password)) {
            throw new MessageException("sw-0001", "请求参数异常 : 发送者邮箱信息未设置");
        }
        if (!MailUtil.checkAddress(from)) {
            throw new MessageException("sw-0001", "请求参数异常 : 邮箱地址");
        }
        if (Objects.isNull(mailMessage) || StringUtils.isBlank(mailMessage.getContent()) || StringUtils.isBlank(mailMessage.getTitle())) {
            throw new MessageException("sw-0001", "请求参数异常 : 邮件内容不能为空");
        }
        if (CollectionUtils.isEmpty(mailMessage.getToAddress())) {
            throw new MessageException("sw-0001", "请求参数异常 : 邮件接收者不能为空");
        }
    }

    /**
     * 进行邮件发送会话初始化
     *
     * @param from 发送者邮箱地址
     * @param password 邮箱密码/授权码
     * @return 邮箱会话
     */
    private Session initSession(String from, String password) {
        Properties properties = new Properties();
        properties.put(MailPropertiesEnum.TRANSPORT_PROTOCOL.getKey(), "smtp");
        properties.put(MailPropertiesEnum.STORE_PROTOCOL.getKey(), "pop3");
        properties.put(MailPropertiesEnum.HOST.getKey(), "smtp" + "." + MailUtil.getMailHost(from));
        properties.put(MailPropertiesEnum.PORT.getKey(), -1);
        properties.put(MailPropertiesEnum.AUTH.getKey(), true);
        properties.put(MailPropertiesEnum.CONNECT_TIMEOUT.getKey(), 10000);
        properties.put(MailPropertiesEnum.TIMEOUT.getKey(), 10000);
        properties.put(MailPropertiesEnum.WRITE_TIMEOUT.getKey(), 10000);
        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        };
        Session session = Session.getDefaultInstance(properties, authenticator);
        if (Boolean.TRUE.equals(mailProperties.getDebugMode())) {
            session.setDebug(true);
        }
        return session;
    }

    /**
     * 进行实际邮件发送对象组装
     *
     * @param mailMessage 待发送邮件内容
     * @param session 邮件发送会话
     * @param from    发送者邮箱地址
     * @return 实际发送邮件对象
     * @throws MessagingException 邮件发送异常
     * @throws UnsupportedEncodingException 不支持编码异常
     */
    private Message buildMessage(MailMessage mailMessage, Session session, String from) throws MessagingException, UnsupportedEncodingException {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));
        // 创建邮件的多个接收者地址
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(String.join(",", mailMessage.getToAddress())));
        // Cc: 抄送（可选）
        if (CollectionUtils.isNotEmpty(mailMessage.getCcAddress())) {
            message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(String.join(",", mailMessage.getCcAddress())));
        }
        // Bcc: 密送（可选）
        if (CollectionUtils.isNotEmpty(mailMessage.getBccAddress())) {
            message.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(String.join(",", mailMessage.getBccAddress())));
        }
        // 设置邮件消息的主题
        message.setSubject(MimeUtility.encodeText(mailMessage.getTitle(), "utf-8", "B"));
        // 设置邮件消息发送的时间
        message.setSentDate(new Date());
        return message;
    }

    /**
     * 根据邮件类型进行发送处理
     *
     * @param message 待发送邮件
     * @param mailType 邮件类型
     * @param content  邮件内容
     * @throws MessageException 消息发送异常
     * @throws MessagingException 邮件发送异常
     */
    private void doSend(Message message, String mailType, String content) throws MessageException, MessagingException {
        MailTypeEnum mailTypeEnum = MailTypeEnum.find(mailType);
        if (Objects.isNull(mailTypeEnum)) {
            throw new MessageException("sw-0001", "请求参数异常 : 非法邮件类型");
        }
        switch (mailTypeEnum) {
            case HTML:
                message.setContent(content, "text/html;charset=UTF-8");
                break;
            case TEXT:
                message.setText(content);
                break;
            default:
                break;
        }
        Transport.send(message);
        message.saveChanges();
    }


}
