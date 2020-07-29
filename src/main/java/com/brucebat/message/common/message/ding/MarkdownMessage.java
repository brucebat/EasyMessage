package com.brucebat.message.common.message.ding;

import com.brucebat.message.common.enums.MessagePropertiesEnum;
import com.brucebat.message.common.enums.MessageTypeEnum;
import com.google.gson.Gson;
import lombok.Data;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @version 1.0
 * @author: Sun Tianyu
 * @date: Created in 2020/7/21
 * @description markdown格式消息类
 */
@Data
public class MarkdownMessage extends BaseMessage {

    /**
     * 标题
     */
    private String title;

    /**
     * 文本
     */
    private String text;
    /**
     * 消息接收人
     */
    private List<String> atMobiles;
    /**
     * 是否通知所有人
     */
    private boolean atAll;

    public MarkdownMessage(){
        this.msgType = MessageTypeEnum.MARKDOWN.getType();
    }

    public MarkdownMessage(String title, String text){
        this.msgType = MessageTypeEnum.MARKDOWN.getType();
        this.title = title;
        this.text = text;
    }

    @Override
    public String toMessage() {
        Gson gson = new Gson();
        Map<String, Object> message = new HashMap<>(3);
        message.put(MessagePropertiesEnum.MSG_TYPE.getValue(), this.msgType);
        Map<String, Object> markdownItems = new HashMap<>(2);
        markdownItems.put(MessagePropertiesEnum.TITLE.getValue(), this.title);
        markdownItems.put(MessagePropertiesEnum.TEXT.getValue(), this.text);
        message.put(MessagePropertiesEnum.MARKDOWM.getValue(), markdownItems);
        Map<String, Object> contactItems = new HashMap<>(2);
        contactItems.put(MessagePropertiesEnum.AT_MOBILES.getValue(), atMobiles);
        contactItems.put(MessagePropertiesEnum.IS_AT_ALL.getValue(), atAll);
        message.put(MessagePropertiesEnum.AT.getValue(), contactItems);
        return gson.toJson(message);
    }
}
