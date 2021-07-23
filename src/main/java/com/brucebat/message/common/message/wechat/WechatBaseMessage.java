package com.brucebat.message.common.message.wechat;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;

/**
 * 微信机器人消息基类
 *
 * @author brucebat
 * @version 1.0
 * @since Created at 2021/5/26 11:39 下午
 */
@Data
public class WechatBaseMessage implements Serializable {

    @JSONField(name = "msgtype")
    protected String msgType;

    @JSONField(serialize = false)
    protected Target target;

    /**
     * 获取微信消息
     *
     * @return 消息内容
     */
    public String toMessage() {
        return JSON.toJSONString(this);
    }
}
