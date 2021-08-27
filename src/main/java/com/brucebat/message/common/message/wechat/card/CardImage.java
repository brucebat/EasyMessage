package com.brucebat.message.common.message.wechat.card;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;

/**
 * 图片样式
 *
 * @author brucebat
 * @version 1.0
 * @since Created at 2021/8/27 10:41 上午
 */
@Data
public class CardImage implements Serializable {

    /**
     * 图片url地址
     */
    private String url;
    /**
     * 图片的宽高比，宽高比要小于2.25，大于1.3，不填该参数默认1.3
     */
    @JSONField(name = "aspect_ratio")
    private String aspectRatio;
}
