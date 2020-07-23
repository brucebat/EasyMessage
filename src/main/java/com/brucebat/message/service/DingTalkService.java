package com.brucebat.message.service;


import com.brucebat.message.common.config.DingTalkProperties;
import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    /**
     * 发送消息
     *
     * @param message 消息内容
     */
    public void send(String message){

    }


    /**
     * 获取发送地址
     *
     * @return
     */
    private String getUrl() {
        if (dingTalkProperties.isSignEnable()){
            long timestamp = System.currentTimeMillis();
            return dingTalkProperties.getNotifyUrl() + "&timestamp=" + timestamp
                    + "&sign=" + sign(timestamp);
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
