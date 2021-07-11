package com.brucebat.message.service.dingtalk;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.brucebat.message.common.enums.DingApiEnum;
import com.brucebat.message.common.enums.DingResponseEnum;
import com.brucebat.message.common.exception.MessageException;
import com.brucebat.message.common.message.ding.DingTemplateWorkNotice;
import com.brucebat.message.common.message.ding.DingWorkNotice;
import com.brucebat.message.common.util.HttpUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

/**
 * 钉钉应用消息服务类
 *
 * @author brucebat
 * @version 1.0
 * @since Created at 2021/4/22 11:01 上午
 */
public class DingAppMessageService {

    /**
     * 发送工作通知
     *
     * @param dingWorkNotice 钉钉工作通知请求参数
     * @param accessToken    调用令牌
     * @return 钉钉服务器异步发送taskId
     * @throws MessageException 消息异常
     */
    public String sendWorkNotice(DingWorkNotice dingWorkNotice, String accessToken) {
        this.validateParams(dingWorkNotice);
        String url = String.format(DingApiEnum.SEND_WORK_NOTICE.getApiPath(), accessToken);
        String response = HttpUtil.post(url, dingWorkNotice);
        return handleResult(response);
    }

    /**
     * 发送模板工作通知
     *
     * @param templateWorkNotice 模板工作通知请求参数
     * @param accessToken        调用令牌
     * @return 钉钉服务器异步发送taskId
     * @throws MessageException 消息异常
     */
    public String sendTemplateWorkNotice(DingTemplateWorkNotice templateWorkNotice, String accessToken) {
        if (Objects.isNull(templateWorkNotice) || StringUtils.isBlank(templateWorkNotice.getTemplateId())) {
            throw new MessageException("sw-0001", "请求参数不能为空");
        }
        String url = String.format(DingApiEnum.SEND_TEMPLATE_WORK_NOTICE.getApiPath(), accessToken);
        String response = HttpUtil.post(url, templateWorkNotice);
        return handleResult(response);
    }

    /**
     * 进行参数校验
     *
     * @param dingWorkNotice 钉钉工作通知
     * @throws MessageException 消息异常
     */
    private void validateParams(DingWorkNotice dingWorkNotice) {
        if (Objects.isNull(dingWorkNotice)) {
            throw new MessageException("sw-0001", "请求参数不能为空: dingWorkNotice为空");
        }
        if (StringUtils.isBlank(dingWorkNotice.getAgentId())) {
            throw new MessageException("sw-001", "请求参数不能为空: agentId为空");
        }
        if (Boolean.FALSE.equals(dingWorkNotice.isToAllUser()) && StringUtils.isBlank(dingWorkNotice.getUserIds()) && StringUtils.isBlank(dingWorkNotice.getDeptIds())) {
            throw new MessageException("sw-001", "请求参数不能为空: 当toAllUser为false, userIds和deptIds不能同时为空");
        }
    }

    /**
     * 进行消息发送结果处理
     *
     * @param response 返回结果
     * @return 钉钉服务器异步发送taskId
     * @throws MessageException 消息异常
     */
    private String handleResult(String response) {
        if (StringUtils.isBlank(response)) {
            throw new MessageException("sw-0010", "发送钉钉通知消息异常: 返回结果为空");
        }
        JSONObject result = JSON.parseObject(response);
        if (0 != result.getIntValue(DingResponseEnum.ERROR_CODE.getFiled())) {
            throw new MessageException("sw-0010", "发送钉钉通知消息异常: " + result.getString(DingResponseEnum.ERROR_MSG.getFiled()));
        }
        return result.getString(DingResponseEnum.TASK_ID.getFiled());
    }


}
