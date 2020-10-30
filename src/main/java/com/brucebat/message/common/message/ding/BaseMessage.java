package com.brucebat.message.common.message.ding;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;

/**
 * @version 1.0
 * @author: Sun Tianyu
 * @since : Created in 2020/7/21
 * 基础信息类
 */
@Data
public class BaseMessage implements Serializable {

    @JSONField(name = "msgtype")
    protected String msgType;

    protected Target at;

}
