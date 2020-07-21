package com.brucebat.message.common.message.ding;

import com.brucebat.message.common.enums.MessageTypeEnum;
import lombok.Data;

import java.util.List;

/**
 * @version 1.0
 * @author: Sun Tianyu
 * @date: Created in 2020/7/22
 * @description
 */
@Data
public class TextMessage extends BaseMessage {

    /**
     * 内容
     */
    private String content;
    /**
     * 指定接受通知者
     */
    private List<String> atMobiles;
    /**
     * 是否通知全体
     */
    private boolean atAll;

    public TextMessage(){
        this.msgType = MessageTypeEnum.TEXT.getType();
    }

    @Override
    public String toMessage() {
        return null;
    }
}
