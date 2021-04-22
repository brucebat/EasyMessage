package com.brucebat.message.common.message.ding;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;

/**
 * @author brucebat
 * @version 1.0
 * @since Created at 2021/4/22 2:54 下午
 */
@Data
public class DingWorkNotice implements Serializable {

    /**
     * 应用id
     */
    @JSONField(name = "agent_id")
    private String agentId;

    /**
     * 接受者用户id列表，使用","分割
     */
    @JSONField(name = "userid_list")
    private String userIds;

    /**
     * 接受部门id列表
     */
    @JSONField(name = "dept_id_list")
    private String deptIds;

    /**
     * 是否发送给所有人
     */
    @JSONField(name = "to_all_user")
    private boolean toAllUser;

    /**
     * 消息内容
     */
    @JSONField(name = "msg")
    private BaseMessage message;
}
