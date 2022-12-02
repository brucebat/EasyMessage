package com.brucebat.message.common.message.ding;

import lombok.Data;

import java.io.Serializable;

/**
 * @author brucebat
 * @version 1.0
 * @since Created at 2022/12/2 11:50
 */
@Data
public class ConversationCreateResponse implements Serializable {

    /**
     * 结果
     */
    private ConversationInfo result;
    /**
     * 是否调用成功
     */
    private Boolean success;
    /**
     * 异常信息
     */
    private String errorMsg;
    /**
     * 异常码
     */
    private Integer errorCode;
    /**
     * 请求id
     */
    private String requestId;

}
