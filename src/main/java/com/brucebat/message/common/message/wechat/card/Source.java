package com.brucebat.message.common.message.wechat.card;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;

/**
 * 模板卡片来源
 *
 * @author brucebat
 * @version 1.0
 * @since Created at 2021/8/25 7:19 下午
 */
@Data
public class Source implements Serializable {

    /**
     * icon图片地址
     */
    @JSONField(name = "icon_url")
    private String iconUrl;

    /**
     * icon描述
     */
    private String desc;
}
