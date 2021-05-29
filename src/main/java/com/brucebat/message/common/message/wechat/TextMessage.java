package com.brucebat.message.common.message.wechat;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.brucebat.message.common.enums.MessageTypeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Objects;

/**
 * 微信机器人text消息
 *
 * @author brucebat
 * @version 1.0
 * @since Created at 2021/5/26 11:44 下午
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class TextMessage extends WechatBaseMessage {

    /**
     * 内容，需要保证为utf-8编码
     */
    private String content;

    public TextMessage() {
        this.msgType = MessageTypeEnum.TEXT.getType();
    }

    @Override
    public String toMessage() {
        JSONObject message = JSON.parseObject(super.toMessage());
        JSONObject text = new JSONObject().fluentPut("content", content);
        if (Objects.nonNull(this.target)) {
            text.fluentPut("mentioned_list", this.target.getUserIds()).fluentPut("mentioned_mobile_list", this.target.getPhoneNumbers());
        }
        return message.toJSONString();
    }
}
