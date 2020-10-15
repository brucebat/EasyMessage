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
 * @since : Created in 2020/7/31
 *
 */
@Data
public class ActionCardMessage extends BaseMessage {

    /**
     * 标题
     */
    private String title;

    /**
     * markdown格式文本
     */
    private String text;

    /**
     * 0-按钮竖直排列，1-按钮横向排列
     */
    private String btnOrientation;

    /**
     * 单个按钮标题
     */
    private String singleTitle;

    /**
     * 单个按钮url地址
     */
    private String singleUrl;


    /**
     * 按钮时间
     */
    private List<Button> buttons;

    public ActionCardMessage() {
        this.msgType = MessageTypeEnum.ACTION_CARD.getType();
    }

    @Override
    public String toMessage() {
        Map<String, Object> actionCardMessage = new HashMap<>(2);
        actionCardMessage.put(MessagePropertiesEnum.MSG_TYPE.getValue(), msgType);
        Map<String, Object> actionItems = new HashMap<>(8);
        actionItems.put(MessagePropertiesEnum.TITLE.getValue(), title);
        actionItems.put(MessagePropertiesEnum.TEXT.getValue(), text);
        actionItems.put(MessagePropertiesEnum.BTN_ORIENTATION.getValue(), btnOrientation);
        if (StringUtils.isEmpty(singleTitle)) {
            actionItems.put(MessagePropertiesEnum.BTNS.getValue(), buttons);
        } else {
            actionItems.put(MessagePropertiesEnum.SINGLE_TITLE.getValue(), singleTitle);
            actionItems.put(MessagePropertiesEnum.SINGLE_URL.getValue(), singleUrl);
        }
        actionCardMessage.put(MessagePropertiesEnum.ACTIONCARD.getValue(), actionItems);
        Gson gson = new Gson();
        return gson.toJson(actionCardMessage);
    }
}
