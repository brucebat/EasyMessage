package com.brucebat.message.common.message.ding;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;

/**
 * 组机器人消息发送返回参数
 *
 * @author brucebat
 * @version 1.0
 * @since Created at 2022/12/3 15:49
 */
@Data
public class GroupBotResponse implements Serializable {

    /**
     * 错误码
     */
    @JSONField(name = "errcode")
    private String errorCode;
    /**
     * 错误信息
     */
    @JSONField(name = "errmsg")
    private String errorMsg;
    /**
     * 是否成功
     */
    @JSONField(name = "success")
    private Boolean success;
    /**
     * 消息id
     */
    @JSONField(name = "open_msg_id")
    private String openMsgId;
    /**
     * 请求参数id
     */
    @JSONField(name = "request_id")
    private String requestId;
}
