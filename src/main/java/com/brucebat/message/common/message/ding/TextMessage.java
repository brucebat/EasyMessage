package com.brucebat.message.common.message.ding;

import com.brucebat.message.common.enums.MessageTypeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.collections4.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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
        if (Objects.nonNull(target) && CollectionUtils.isNotEmpty(target.getAtMobiles())) {
            StringBuilder atText = new StringBuilder("\n\n");
            for (String phone : target.getAtMobiles()) {
                atText.append("@").append(phone);
            }
            content = content + atText;
        }
        TextMessage textMessage = new TextMessage();
        if (Objects.nonNull(target)) {
            textMessage.setAt(target);
        }
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
