package com.brucebat.message.common.message.wechat.card;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 模板卡片
 *
 * @author brucebat
 * @version 1.0
 * @since Created at 2021/8/25 7:15 下午
 */
@Data
public class TemplateCard implements Serializable {
    /**
     * 模版卡片的模版类型，文本通知模版卡片的类型为text_notice，图文展示模版卡片的类型为news_notice
     */
    @JSONField(name = "card_type")
    private String cardType;
    /**
     * 消息来源
     */
    private Source source;
    /**
     * 主标题
     */
    @JSONField(name = "main_title")
    private MainTitle mainTitle;
    /**
     * 图片样式，用于图片卡片样式
     */
    @JSONField(name = "card_image")
    private CardImage cardImage;
    /**
     * 关键数据样式内容，用于文本卡片消息
     */
    @JSONField(name = "emphasis_content")
    private Content emphasisContent;
    /**
     * 卡片二级垂直内容，该字段可为空数组，但有数据的话需确认对应字段是否必填，列表长度不超过4, 用于图片卡片消息
     */
    @JSONField(name = "vertical_content")
    private Content verticalContent;
    /**
     * 二级标题和文本列表，该字段可为空数组，但有数据的话需确认对应字段是否必填，列表长度不超过6
     */
    @JSONField(name = "horizontal_content_list")
    private List<HorizontalContent> horizontalContentList;
    /**
     * 二级
     */
    private String subTitleText;
    /**
     * 跳转指引样式的列表，该字段可为空数组，但有数据的话需确认对应字段是否必填，列表长度不超过3
     */
    @JSONField(name = "jump_list")
    private List<JumpEvent> jumpList;
    /**
     * 整体卡片的点击跳转事件，news_notice模版卡片中该字段为必填项
     */
    @JSONField(name = "card_action")
    private JumpEvent cardAction;
}
