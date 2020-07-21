package com.brucebat.message.common.message.ding;

import java.io.Serializable;

/**
 * @version 1.0
 * @author: Sun Tianyu
 * @date: Created in 2020/7/21
 * @description 基础信息类
 */
public abstract class BaseMessage implements Serializable {

    protected String msgType;

    public abstract String toMessage();

}
