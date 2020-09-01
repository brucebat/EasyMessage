package com.brucebat.message.service;


import com.brucebat.message.common.annotation.Limiter;
import com.brucebat.message.common.config.DingTalkProperties;
import com.brucebat.message.common.exception.MessageException;
import com.brucebat.message.common.message.ding.BaseMessage;
import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLEncoder;

/**
 * @version 1.0
 * @author: Sun Tianyu
 * @date: Created in 2020/7/22
 * @description 钉钉消息服务
 */
public class DingTalkService {

    private static final Logger log = LoggerFactory.getLogger(DingTalkService.class);

    private DingTalkProperties dingTalkProperties;


    @Autowired
    private RestTemplate restTemplate;

    public DingTalkService(DingTalkProperties dingTalkProperties) {
        this.dingTalkProperties = dingTalkProperties;
    }

    /**
     * 发送消息
     * 增加限流处理，每分钟最多发送20次
     *
     * @param message 消息内容
     * @throws MessageException 消息异常
     */
    @Limiter(permitsPerSecond = 0.3)
    public void sendWithLimit(BaseMessage message) throws MessageException {
        send(message);
    }

    /**
     * 发送消息
     *
     * @param message 消息内容
     */
    public void send(BaseMessage message) throws MessageException {
        String url = getUrl();
        if (StringUtils.isEmpty(url)) {
            throw new MessageException("sw-0001", "钉钉发送地址获取失败");
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(message.toMessage(), headers);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, entity, String.class);
        log.info("钉钉发送结果：{}", responseEntity.toString());
        if (!responseEntity.getStatusCode().is2xxSuccessful()) {
            log.error("钉钉发送异常,结果为：{}", responseEntity.getBody());
            throw new MessageException("sw-0002", "钉钉发送异常");
        }
    }


    /**
     * 获取发送地址
     *
     * @return 返回签名地址
     */
    private String getUrl() {
        if (dingTalkProperties.isSignEnable()) {
            long timestamp = System.currentTimeMillis();
            String signature = sign(timestamp);
            if (StringUtils.isEmpty(signature)) {
                return null;
            }
            return dingTalkProperties.getNotifyUrl() + "&timestamp=" + timestamp
                    + "&sign=" + signature;
        }
        return dingTalkProperties.getNotifyUrl();
    }

    /**
     * 进行签名计算
     *
     * @param timestamp 时间戳
     * @return 签名
     */
    private String sign(long timestamp) {
        String signStr = timestamp + "\n" + dingTalkProperties.getSignature();
        String sign = null;
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(dingTalkProperties.getSignature().getBytes("UTF-8"), "HmacSHA256"));
            byte[] signData = mac.doFinal(signStr.getBytes("UTF-8"));
            sign = URLEncoder.encode(new String(Base64.encodeBase64(signData)), "UTF-8");
        } catch (Exception e) {
            log.error("[Sign error] something wrong happened, caused by : {}", e);
        }
        return sign;
    }
}
