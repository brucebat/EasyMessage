package com.brucebat.message.common.message.wechat.card;

import lombok.Data;

import java.io.Serializable;

/**
 * 主标题
 *
 * @author brucebat
 * @version 1.0
 * @since Created at 2021/8/25 7:21 下午
 */
@Data
public class MainTitle implements Serializable {

    /**
     * 主标题
     */
    private String title;
    /**
     * 主标题描述
     */
    private String desc;
}
