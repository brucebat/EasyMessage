package com.brucebat.message.common.message.wechat.card;

import lombok.Data;

import java.io.Serializable;

/**
 * 消息数据内容
 *
 * @author brucebat
 * @version 1.0
 * @since Created at 2021/8/25 7:22 下午
 */
@Data
public class Content implements Serializable {

    /**
     * 关键数据样式标题
     */
    private String title;
    /**
     * 关键数据样式描述
     */
    private String desc;
}
