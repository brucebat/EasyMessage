package com.brucebat.message.common.message.ding;

import com.brucebat.message.common.enums.MessagePropertiesEnum;
import com.brucebat.message.common.enums.MessageTypeEnum;
import com.google.gson.Gson;
import lombok.Data;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public TextMessage() {
        this.msgType = MessageTypeEnum.TEXT.getType();
    }

    @Override
    public String toMessage() {
        if (StringUtils.isEmpty(msgType) || StringUtils.isEmpty(content)) {
            return null;
        }
        Gson gson = new Gson();
        Map<String, Object> textMessage = new HashMap<>(3);
        textMessage.put(MessagePropertiesEnum.MSG_TYPE.getValue(), msgType);
        Map<String, String> textItems = new HashMap<>(1);
        textItems.put(MessagePropertiesEnum.CONTENT.getValue(), content);
        textMessage.put(MessagePropertiesEnum.TEXT.getValue(), textItems);
        Map<String, Object> contacts = new HashMap<>(2);
        contacts.put(MessagePropertiesEnum.AT_MOBILES.getValue(), atMobiles);
        contacts.put(MessagePropertiesEnum.IS_AT_ALL.getValue(), atAll);
        textMessage.put(MessagePropertiesEnum.AT.getValue(), contacts);
        return gson.toJson(textMessage);
    }
}
