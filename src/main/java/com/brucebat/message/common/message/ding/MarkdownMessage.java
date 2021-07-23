package com.brucebat.message.common.message.ding;


import com.brucebat.message.common.enums.MessageTypeEnum;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Objects;


/**
 * 钉钉markdown类型消息类
 *
 * @author : Sun Tianyu
 * @version 1.0
 * @since : Created in 2020/7/21
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors
public class MarkdownMessage extends DingTalkBaseMessage {

    private Markdown markdown;

    public MarkdownMessage() {
        this.msgType = MessageTypeEnum.MARKDOWN.getType();
    }

    public static MarkdownMessage build(String title, String text, Target target) {
        if (Objects.nonNull(target) && CollectionUtils.isNotEmpty(target.getAtMobiles())) {
            StringBuilder atText = new StringBuilder("\n\n");
            for (String phone : target.getAtMobiles()) {
                atText.append("@").append(phone);
            }
            text = text + atText;
        }
        Markdown markdown = new Markdown(title, text);
        MarkdownMessage markdownMessage = new MarkdownMessage();
        if (Objects.nonNull(target)) {
            markdownMessage.setAt(target);
        }
        markdownMessage.setMarkdown(markdown);
        return markdownMessage;
    }
}

@Data
class Markdown {

    /**
     * 标题
     */
    private String title;
    /**
     * 内容
     */
    private String text;

    public Markdown(String title, String text) {
        this.title = title;
        this.text = text;
    }
}
