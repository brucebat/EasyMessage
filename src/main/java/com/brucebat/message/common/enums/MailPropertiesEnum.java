package com.brucebat.message.common.enums;

/**
 * @author Sun Tianyu
 * @version 1.0
 * @since Created in 2021/2/8
 */
public enum MailPropertiesEnum {

    /**
     * 发送协议
     */
    TRANSPORT_PROTOCOL("mail.transport.protocol"),
    /**
     * 保存协议
     */
    STORE_PROTOCOL("mail.store.protocol"),
    /**
     * 邮箱服务器主机名
     */
    HOST("mail.host"),
    /**
     * 邮箱服务器端口号
     */
    PORT("mail.port"),
    /**
     * 是否进行安全验证
     */
    AUTH("mail.smtp.auth"),
    /**
     * 连接超时时间
     */
    CONNECT_TIMEOUT("mail.smtp.connectiontimeout"),
    /**
     * 接收邮件超时时间
     */
    TIMEOUT("mail.smtp.timeout"),
    /**
     * 写入邮件超时时间
     */
    WRITE_TIMEOUT("mail.smtp.writetimeout");

    private String key;

    MailPropertiesEnum(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
