package com.brucebat.message.common.message.ding;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * @version 1.0
 * @author: Sun Tianyu
 * @since : Created in 2020/8/1
 *
 */
@Data
public class FeedCard {

    /**
     * 标题
     */
    private String title;
    /**
     * 消息地址
     */
    @SerializedName("messageURL")
    private String messageUrl;
    /**
     * 封面地址
     */
    @SerializedName("picURL")
    private String picUrl;
}
