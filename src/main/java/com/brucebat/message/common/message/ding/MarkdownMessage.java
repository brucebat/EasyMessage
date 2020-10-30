package com.brucebat.message.common.message.ding;


import com.brucebat.message.common.enums.MessageTypeEnum;

import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * @version 1.0
 * @author: Sun Tianyu
 * @since : Created in 2020/7/21
 * markdown格式消息类
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MarkdownMessage extends BaseMessage {

    private Markdown markdown;

    public MarkdownMessage() {
        this.msgType = MessageTypeEnum.MARKDOWN.getType();
    }

    public static MarkdownMessage build(String title, String text, Target target) {
        Markdown markdown = new Markdown(title, text);
        MarkdownMessage markdownMessage = new MarkdownMessage();
        markdownMessage.setAt(target);
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
