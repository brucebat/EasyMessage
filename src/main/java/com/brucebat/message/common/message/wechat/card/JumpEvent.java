package com.brucebat.message.common.message.wechat.card;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 跳转事件类
 *
 * @author brucebat
 * @version 1.0
 * @since Created at 2021/8/27 10:21 上午
 */
@Data
public class JumpEvent implements Serializable {
    /**
     * 跳转链接类型，0或不填代表不是链接，1 - 代表跳转url，2 - 代表跳转小程序
     */
    private Integer type;
    /**
     * 跳转链接样式的文案内容，建议不超过13个字
     */
    private String title;
    /**
     * 跳转链接的url，type是1时必填
     */
    private String url;
    /**
     * 跳转链接的小程序的appid，type是2时必填
     */
    @JSONField(name = "appid")
    private String appId;
    /**
     * 跳转链接的小程序的pagepath，type是2时选填
     */
    @JSONField(name = "pagepath")
    private String pagePath;
}
