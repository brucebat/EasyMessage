package com.brucebat.message.common.message.wechat;

import com.alibaba.fastjson.annotation.JSONField;
import com.brucebat.message.common.message.wechat.card.TemplateCard;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 模板卡片消息
 *
 * @author brucebat
 * @version 1.0
 * @since Created at 2021/8/25 7:07 下午
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class TemplateCardMessage extends WechatBaseMessage implements Serializable {

    /**
     * 模板卡片消息
     */
    @JSONField(name = "template_card")
    private TemplateCard templateCard;

    public TemplateCardMessage () {
        this.msgType = "template_card";
    }

    public TemplateCardMessage(TemplateCard templateCard) {
        this.msgType = "template_card";
        this.templateCard = templateCard;
    }
}
