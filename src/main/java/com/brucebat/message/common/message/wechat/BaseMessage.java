package com.brucebat.message.common.message.wechat;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;

/**
 * 微信机器人消息基类
 *
 * @author brucebat
 * @version 1.0
 * @since Created at 2021/5/26 11:39 下午
 */
@Data
public class BaseMessage implements Serializable {

    @JSONField(name = "msgtype")
    private String msgType;
}
