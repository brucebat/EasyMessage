package com.brucebat.message.common.message.ding;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;

/**
 * @author brucebat
 * @version 1.0
 * @since Created at 2022/12/2 11:51
 */
@Data
public class ConversationInfo implements Serializable {

    /**
     * 群id
     */
    @JSONField(name = "open_conversation_id")
    private String openConversationId;
    /**
     * 会话id
     */
    @JSONField(name = "chat_id")
    private String chatId;
}
