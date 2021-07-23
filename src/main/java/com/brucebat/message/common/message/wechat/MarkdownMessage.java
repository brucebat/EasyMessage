package com.brucebat.message.common.message.wechat;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.brucebat.message.common.enums.MessageTypeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

/**
 * 微信markdown消息
 *
 * @author brucebat
 * @version 1.0
 * @since Created at 2021/5/29 11:44 上午
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MarkdownMessage extends WechatBaseMessage{

    /**
     * 消息内容, 需保证为utf-8
     */
    private String content;

    public MarkdownMessage() {
        this.msgType = MessageTypeEnum.MARKDOWN.getType();
    }

    @Override
    public String toMessage() {
        JSONObject message = JSON.parseObject(super.toMessage());
        if (StringUtils.isNotBlank(this.content) && Objects.nonNull(this.target) &&
                CollectionUtils.isNotEmpty(this.target.getUserIds())) {
            StringBuilder userIds = new StringBuilder();
            for (String userId : this.target.getUserIds()) {
                userIds.append("<").append("@").append(userId).append("> ");
            }
            this.content = this.content + "\n" + userIds;
        }
        JSONObject content = new JSONObject().fluentPut("content", this.content);
        message.put("markdown", content);
        return message.toJSONString();
    }
}
