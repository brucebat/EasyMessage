package com.brucebat.message.common.message.ding;

import com.brucebat.message.common.enums.MessagePropertiesEnum;
import com.brucebat.message.common.enums.MessageTypeEnum;
import com.google.gson.Gson;
import lombok.Data;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @version 1.0
 * @author: Sun Tianyu
 * @date: Created in 2020/8/1
 * @description
 */
@Data
public class FeedCardMessage extends BaseMessage {

    /**
     * 跳转链接
     */
    private List<FeedCard> links;

    public FeedCardMessage(){
        this.msgType = MessageTypeEnum.FEED_CARD.getType();
    }

    @Override
    public String toMessage() {
        Map<String, Object> feedCard = new HashMap<>(2);
        feedCard.put(MessagePropertiesEnum.MSG_TYPE.getValue(), msgType);
        Map<String, Object> feedCardItems = new HashMap<>(1);
        feedCardItems.put(MessagePropertiesEnum.LINKS.getValue(), links);
        feedCard.put(MessagePropertiesEnum.FEEDCARD.getValue(), feedCardItems);
        Gson gson = new Gson();
        return gson.toJson(feedCard);
    }

}
