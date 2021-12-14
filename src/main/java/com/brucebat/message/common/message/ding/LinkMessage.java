package com.brucebat.message.common.message.ding;


import com.brucebat.message.common.enums.MessageTypeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 钉钉Link类型消息
 *
 * @version 1.0
 * @author : Sun Tianyu
 * @since : Created in 2020/7/29
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors
public class LinkMessage extends DingTalkBaseMessage {

    private Link link;

    public LinkMessage() {
        this.msgType = MessageTypeEnum.LINK.getType();
    }

    public static LinkMessage build(String title, String text, String picUrl, String messageUrl) {
        Link link = new Link(title, text, picUrl, messageUrl);
        LinkMessage linkMessage = new LinkMessage();
        linkMessage.setLink(link);
        return linkMessage;
    }

    @Data
    public static class Link {
        /**
         * 标题
         */
        private String title;
        /**
         * 消息内容
         */
        private String text;
        /**
         * 封面url地址
         */
        private String picUrl;
        /**
         * 跳转消息内容
         */
        private String messageUrl;

        public Link() {

        }

        public Link(String title, String text, String picUrl, String messageUrl) {
            this.title = title;
            this.text = text;
            this.picUrl = picUrl;
            this.messageUrl = messageUrl;
        }
    }

}


