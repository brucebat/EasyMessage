package com.brucebat.message.common.message.ding;

import com.alibaba.fastjson.annotation.JSONField;
import com.brucebat.message.common.enums.MessageTypeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 钉钉ActionCard类型消息
 *
 * @author : Sun Tianyu
 * @version 1.0
 * @since : Created in 2020/7/31
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class ActionCardMessage extends DingTalkBaseMessage {

    private ActionCard actionCard;

    public ActionCardMessage() {
        this.msgType = MessageTypeEnum.ACTION_CARD.getType();
    }

    /**
     * 单个按钮独立跳转
     *
     * @param title          标题
     * @param text           按钮内容
     * @param btnOrientation 按钮样式
     * @param singleTitle    按钮标题
     * @param singleUrl      按钮地址
     * @return 创建完的事件消息对象
     */
    public static ActionCardMessage buildSingleButton(String title, String text, String btnOrientation, String singleTitle, String singleUrl) {
        return build(title, text, btnOrientation, singleTitle, singleUrl, null);
    }

    /**
     * 多个按钮跳转的事件消息
     *
     * @param title          标题
     * @param text           文本内容
     * @param btnOrientation 按钮样式
     * @return 创建完的事件消息
     */
    public static ActionCardMessage buildMultipleButtons(String title, String text, String btnOrientation) {
        return build(title, text, btnOrientation, null, null, null);
    }

    /**
     * 多个按钮跳转的事件消息
     *
     * @param title          标题
     * @param text           文本内容
     * @param btnOrientation 按钮样式
     * @param buttons        按钮
     * @return 创建完的事件消息
     */
    public static ActionCardMessage buildMultipleButtons(String title, String text, String btnOrientation, List<Button> buttons) {
        return build(title, text, btnOrientation, null, null, buttons);
    }


    private static ActionCardMessage build(String title, String text, String btnOrientation, String singleTitle, String singleUrl, List<Button> buttons) {
        ActionCardMessage actionCardMessage = new ActionCardMessage();
        ActionCard actionCard = new ActionCard();
        actionCard.setTitle(title);
        actionCard.setText(text);
        actionCard.setBtnOrientation(btnOrientation);
        if (StringUtils.isNotBlank(singleTitle) && StringUtils.isNotBlank(singleUrl)) {
            actionCard.setSingleTitle(singleTitle);
            actionCard.setSingleUrl(singleUrl);
        }
        if (CollectionUtils.isNotEmpty(buttons)) {
            actionCard.setButtons(buttons);
        }
        actionCardMessage.setActionCard(actionCard);
        return actionCardMessage;
    }

    /**
     * 事件卡片新增按钮
     *
     * @param title     按钮标题
     * @param actionUrl 事件跳转url
     * @return 事件卡片对象
     */
    public ActionCardMessage addButton(String title, String actionUrl) {
        Button button = new Button();
        button.setTitle(title);
        button.setActionUrl(actionUrl);
        if (Objects.isNull(this.actionCard)) {
            this.actionCard = new ActionCard();
            this.actionCard.setTitle(title);
            this.actionCard.setBtnOrientation("0");
            this.actionCard.setText(title);
        }
        if (CollectionUtils.isEmpty(this.actionCard.getButtons())) {
            this.actionCard.setButtons(new ArrayList<>());
        }
        this.actionCard.getButtons().add(button);
        return this;
    }

}

@Data
class ActionCard {
    /**
     * 首屏会话透出的展示内容
     */
    private String title;
    /**
     * markdown格式的消息
     */
    private String text;
    /**
     * 0-按钮竖直排列，1-按钮横向排列
     */
    private String btnOrientation;
    /**
     * 单个按钮的标题。(设置此项和singleURL后btns无效)
     */
    private String singleTitle;
    /**
     * 点击singleTitle按钮触发的URL
     */
    @JSONField(name = "singleURL")
    private String singleUrl;

    /**
     * 按钮
     */
    @JSONField(name = "btns")
    private List<Button> buttons;
}

@Data
class Button {
    /**
     * 按钮标题
     */
    private String title;
    /**
     * 点击按钮触发的URL
     */
    @JSONField(name = "actionURL")
    private String actionUrl;
}