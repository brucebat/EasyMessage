package com.brucebat.message.common.message.wechat;

import com.brucebat.message.common.message.wechat.news.News;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 图文消息类型
 *
 * @author brucebat
 * @version 1.0
 * @since Created at 2021/8/27 11:57 上午
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class NewsMessage extends WechatBaseMessage implements Serializable {

    private News news;

    public NewsMessage() {
        this.msgType = "news";
    }
}
