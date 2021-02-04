package com.brucebat.message.common.message.ding;

import com.brucebat.message.common.enums.MessageTypeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 钉钉文本类消息
 *
 * @version 1.0
 * @author : Sun Tianyu
 * @since : Created in 2020/7/22
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class TextMessage extends BaseMessage {

    private Text text;

    public TextMessage() {
        this.msgType = MessageTypeEnum.TEXT.getType();
    }

    public static TextMessage build(String content, Target target) {
        TextMessage textMessage = new TextMessage();
        textMessage.setAt(target);
        Text text = new Text(content);
        textMessage.setText(text);
        return textMessage;
    }


}

@Data
class Text {
    /**
     * 内容
     */
    String content;

    public Text(String content) {
        this.content = content;
    }
}
