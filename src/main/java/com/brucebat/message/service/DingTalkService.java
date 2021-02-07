package com.brucebat.message.service;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.brucebat.message.common.config.DingTalkProperties;
import com.brucebat.message.common.exception.MessageException;
import com.brucebat.message.common.message.ding.BaseMessage;
import com.brucebat.message.common.util.HttpUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Objects;

/**
 * 钉钉消息服务
 *
 * @author : Sun Tianyu
 * @version 1.0
 * @since : Created in 2020/7/22
 */
public class DingTalkService {

    private static final Logger log = LoggerFactory.getLogger(DingTalkService.class);

    /**
     * 钉钉机器人配置信息
     */
    private final DingTalkProperties dingTalkProperties;

    /**
     * 钉钉机器人消息推送地址
     */
    private static final String DING_ROBOT_NOTIFY_URL = "https://oapi.dingtalk.com/robot/send?access_token=%s";

    /**
     * 编码集
     */
    private static final String ENCODE_UTF_8 = "UTF-8";

    /**
     * 错误码
     */
    private static final String ERROR_CODE = "errcode";

    public DingTalkService(DingTalkProperties dingTalkProperties) {
        this.dingTalkProperties = dingTalkProperties;
    }

    /**
     * 根据本地配置的发送信息进行钉钉机器人消息推送
     *
     * @param message 消息内容
     * @return 钉钉消息推送结果
     * @throws MessageException 消息发送异常
     */
    public boolean send(BaseMessage message) throws MessageException {
        if (Objects.isNull(dingTalkProperties) || StringUtils.isBlank(dingTalkProperties.getAccessToken())) {
            throw new MessageException("sw-0001", "未配置钉钉机器人消息发送所需的信息");
        }
        return send(message, dingTalkProperties.getAccessToken(), dingTalkProperties.isEnableSign(), dingTalkProperties.getEncryptKey());
    }

    /**
     * 根据关键词和令牌进行发送（关键词请设置到内容中）
     *
     * @param message     待发送内容
     * @param accessToken 令牌
     * @return 是否发送成功
     * @throws MessageException 消息推送异常
     */
    public boolean send(BaseMessage message, String accessToken) throws MessageException {
        if (StringUtils.isBlank(accessToken)) {
            throw new MessageException("sw-0001", "钉钉机器人令牌不存在");
        }
        return send(message, accessToken, false, null);
    }

    /**
     * 根据机器人令牌、是否签名以及密钥进行机器人消息发送
     *
     * @param message     待发送消息
     * @param accessToken 令牌
     * @param enableSign  是否签名
     * @param encryptKey  签名密钥
     * @return 是否发送成功
     * @throws MessageException 消息发送异常
     */
    public boolean send(BaseMessage message, String accessToken, boolean enableSign, String encryptKey) throws MessageException {
        String url = getUrl(accessToken, enableSign, encryptKey);
        if (StringUtils.isBlank(url)) {
            throw new MessageException("sw-0001", "钉钉发送地址获取失败");
        }
        String response = HttpUtil.post(url, message);
        log.info("钉钉发送结果：{}", response);
        if (StringUtils.isBlank(response)) {
            throw new MessageException("sw-0010", "钉钉机器人消息推送异常");
        }
        JSONObject data = JSON.parseObject(response);
        int code = data.getInteger(ERROR_CODE);
        return code == 0;
    }

    /**
     * 获取钉钉机器人推送地址
     *
     * @param accessToken 令牌
     * @param enableSign  是否签名
     * @param encryptKey  签名密钥
     * @return 获取机器人推送地址
     * @throws MessageException 消息发送异常
     */
    private String getUrl(String accessToken, boolean enableSign, String encryptKey) throws MessageException {
        if (Boolean.TRUE.equals(enableSign)) {
            if (StringUtils.isBlank(encryptKey)) {
                throw new MessageException("sw-0001", "param error: encrypt key missing!");
            }
            long timestamp = System.currentTimeMillis();
            String signature = sign(timestamp, encryptKey);
            if (StringUtils.isBlank(signature)) {
                return null;
            }
            return String.format(DING_ROBOT_NOTIFY_URL, accessToken) + "&timestamp=" + timestamp
                    + "&sign=" + signature;
        }
        return String.format(DING_ROBOT_NOTIFY_URL, accessToken);
    }

    /**
     * 根据密钥和时间戳进行实际签名
     *
     * @param timestamp  时间戳
     * @param encryptKey 密钥
     * @return 签名
     */
    private String sign(long timestamp, String encryptKey) {
        String signStr = timestamp + "\n" + encryptKey;
        String sign = null;
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(encryptKey.getBytes(StandardCharsets.UTF_8), "HmacSHA256"));
            byte[] signData = mac.doFinal(signStr.getBytes(StandardCharsets.UTF_8));
            sign = URLEncoder.encode(new String(Base64.getEncoder().encode(signData)), ENCODE_UTF_8);
        } catch (Exception e) {
            log.error("[Sign error] something wrong happened, caused by : {}", e);
        }
        return sign;
    }
}
