package com.brucebat.message.common.exception;

import lombok.Data;

/**
 * @version 1.0
 * @author: Sun Tianyu
 * @since : Created in 2020/7/25
 *
 */
@Data
public class MessageException extends Exception {

    private String errorCode;
    private String errorMsg;

    public MessageException(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }
}
