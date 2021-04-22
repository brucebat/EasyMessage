package com.brucebat.message.common.message.ding;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;

/**
 * 钉钉模板通知消息
 *
 * @author brucebat
 * @version 1.0
 * @since Created at 2021/4/22 3:37 下午
 */
@Data
public class DingTemplateWorkNotice implements Serializable {

    /**
     * 应用id
     */
    @JSONField(name = "agent_id")
    private String agentId;

    /**
     * 模板id
     */
    @JSONField(name = "template_id")
    private String templateId;

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
     * 消息模板动态参数赋值数据, 格式为jsonString
     */
    private String data;

}
