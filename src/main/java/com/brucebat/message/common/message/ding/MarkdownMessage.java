package com.brucebat.message.common.message.ding;

import com.brucebat.message.common.enums.MessageTypeEnum;
import lombok.Data;

/**
 * @version 1.0
 * @author: Sun Tianyu
 * @date: Created in 2020/7/21
 * @description
 */
@Data
public class MarkdownMessage extends BaseMessage {

    public MarkdownMessage(){
        this.msgType = MessageTypeEnum.MARKDOWN.getType();
    }

    @Override
    public String toMessage() {
        return null;
    }
}
