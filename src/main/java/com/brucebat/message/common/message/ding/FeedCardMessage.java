package com.brucebat.message.common.message.ding;

import com.alibaba.fastjson.annotation.JSONField;
import com.brucebat.message.common.enums.MessageTypeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.collections4.CollectionUtils;

import java.util.*;

/**
 * @version 1.0
 * @author: Sun Tianyu
 * @since : Created in 2020/8/1
 *
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class FeedCardMessage extends BaseMessage {

    private FeedCard feedCard;

    public FeedCardMessage(){
        this.msgType = MessageTypeEnum.FEED_CARD.getType();
    }

    public static FeedCardMessage build(String title, String messageUrl, String picUrl) {
        FeedCardLink feedCardLink = new FeedCardLink(title, messageUrl, picUrl);
        FeedCard feedCard = new FeedCard();
        List<FeedCardLink> links = new ArrayList<>();
        links.add(feedCardLink);
        feedCard.setLinks(links);
        FeedCardMessage feedCardMessage = new FeedCardMessage();
        feedCardMessage.setFeedCard(feedCard);
        return feedCardMessage;
    }

    /**
     * 增加链接信息
     *
     * @param title 标题
     * @param messageUrl 消息地址
     * @param picUrl 图片地址
     * @return 返回流消息
     */
    public FeedCardMessage addLink(String title, String messageUrl, String picUrl) {
        FeedCardLink feedCardLink = new FeedCardLink(title, messageUrl, picUrl);
        if (Objects.isNull(this.feedCard)) {
            this.feedCard = new FeedCard();
        }
        List<FeedCardLink> links = CollectionUtils.isNotEmpty(this.feedCard.getLinks()) ? this.feedCard.getLinks() : new ArrayList<>();
        links.add(feedCardLink);
        this.feedCard.setLinks(links);
        return this;
    }

}

@Data
class FeedCard {
    private List<FeedCardLink> links;
}

@Data
class FeedCardLink {
    /**
     * 标题
     */
    private String title;
    /**
     * 消息地址
     */
    @JSONField(name = "messageURL")
    private String messageUrl;
    /**
     * 封面地址
     */
    @JSONField(name = "picURL")
    private String picUrl;

    public FeedCardLink(String title, String messageUrl, String picUrl) {
        this.title = title;
        this.messageUrl = messageUrl;
        this.picUrl = picUrl;
    }
}
