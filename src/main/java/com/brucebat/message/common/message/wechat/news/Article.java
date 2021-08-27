package com.brucebat.message.common.message.wechat.news;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;

/**
 * 图文信息类
 *
 * @author brucebat
 * @version 1.0
 * @since Created at 2021/8/27 11:59 上午
 */
@Data
public class Article implements Serializable {
    /**
     * 标题
     */
    private String title;
    /**
     * 描述
     */
    private String description;
    /**
     * 文章跳转url
     */
    private String url;
    /**
     * 图文消息的图片链接
     */
    @JSONField(name = "picurl")
    private String picUrl;
}
