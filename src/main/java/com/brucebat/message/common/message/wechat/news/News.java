package com.brucebat.message.common.message.wechat.news;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 图文消息类
 *
 * @author brucebat
 * @version 1.0
 * @since Created at 2021/8/27 11:58 上午
 */
@Data
public class News implements Serializable {

    /**
     * 图文消息，一个图文消息支持1到8条图文
     */
    private List<Article> articles;
}
