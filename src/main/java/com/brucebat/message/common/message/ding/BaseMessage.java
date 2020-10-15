package com.brucebat.message.common.message.ding;

import lombok.Data;

import java.io.Serializable;

/**
 * @version 1.0
 * @author: Sun Tianyu
 * @since : Created in 2020/7/21
 *  基础信息类
 */
@Data
public abstract class BaseMessage implements Serializable {

    protected String msgType;

    public abstract String toMessage();

}
