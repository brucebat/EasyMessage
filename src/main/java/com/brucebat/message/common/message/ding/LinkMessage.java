package com.brucebat.message.common.message.ding;

import com.brucebat.message.common.enums.MessagePropertiesEnum;
import com.brucebat.message.common.enums.MessageTypeEnum;
import com.google.gson.Gson;
import lombok.Data;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @version 1.0
 * @author: Sun Tianyu
 * @date: Created in 2020/7/29
 * @description
 */
@Data
public class LinkMessage extends BaseMessage {

    /**
     * 标题
     */
    private String title;
    /**
     * 文本
     */
    private String text;
    /**
     * 消息url
     */
    private String messageUrl;
    /**
     * 图片url
     */
    private String picUrl;

    public LinkMessage() {
        this.msgType = MessageTypeEnum.LINK.getType();
    }

    @Override
    public String toMessage() {
        Gson gson = new Gson();
        Map<String, Object> message = new HashMap<>(2);
        message.put(MessagePropertiesEnum.MSG_TYPE.getValue(), msgType);
        Map<String, String> linkItems = new HashMap<>();
        linkItems.put(MessagePropertiesEnum.TITLE.getValue(), title);
        linkItems.put(MessagePropertiesEnum.TEXT.getValue(), text);
        if (!StringUtils.isEmpty(picUrl)) {
            linkItems.put(MessagePropertiesEnum.PIC_URL.getValue(), picUrl);
        }
        linkItems.put(MessagePropertiesEnum.MESSAGE_URL.getValue(), messageUrl);
        return gson.toJson(message);
    }
}
