package com.brucebat.message.common.message.wechat.card;

import lombok.Data;

import java.io.Serializable;

/**
 * 二级标题和文本
 *
 * @author brucebat
 * @version 1.0
 * @since Created at 2021/8/25 7:25 下午
 */
@Data
public class HorizontalContent implements Serializable {

    /**
     * 链接类型，0或不填代表是普通文本，1 - 代表跳转url，2 - 代表下载附件
     */
    private Integer type;
    /**
     *  二级标题，建议不超过5个字
     */
    private String keyName;
    /**
     * 二级文本，如果type是2，该字段代表文件名称（要包含文件类型），建议不超过26个字
     */
    private String value;
    /**
     * 链接跳转的url，type是1时必填
     */
    private String url;
    /**
     * 附件的mediaId，type是2时必填
     */
    private String mediaId;
}
