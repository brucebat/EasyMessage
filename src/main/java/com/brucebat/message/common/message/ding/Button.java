package com.brucebat.message.common.message.ding;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * @version 1.0
 * @author: Sun Tianyu
 * @since : Created in 2020/7/31
 *
 */
@Data
public class Button {

    /**
     * 标题
     */
    private String title;

    /**
     * 事件url地址
     */
    @SerializedName("actionURL")
    private String actionUrl;
}
