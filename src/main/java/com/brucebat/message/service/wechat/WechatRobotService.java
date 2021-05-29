package com.brucebat.message.service.wechat;

import com.brucebat.message.common.exception.MessageException;
import com.brucebat.message.common.message.wechat.WechatBaseMessage;
import com.brucebat.message.common.util.HttpUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

/**
 * 微信机器人消息推送服务
 *
 * @author brucebat
 * @version 1.0
 * @since Created at 2021/5/26 11:30 下午
 */
public class WechatRobotService {

    private static final String WECHAT_ROBOT_NOTIFY_URL = "https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key=%s";

    /**
     * 发送机器人消息
     *
     * @param message  微信机器人消息
     * @param robotKey 机器人key
     * @return 发送结果
     */
    public String sendRobotMessage(WechatBaseMessage message, String robotKey) throws MessageException {
        this.validateParams(message, robotKey);
        String url = String.format(WECHAT_ROBOT_NOTIFY_URL, robotKey);
        return HttpUtil.post(url, message.toMessage());
    }

    /**
     * 进行参数校验
     *
     * @param message  机器人消息
     * @param robotKey 机器人key
     */
    private void validateParams(WechatBaseMessage message, String robotKey) throws MessageException {
        if (Objects.isNull(message)) {
            throw new MessageException("sw-0001", "请求参数不能为空: message不能为空");
        }
        if (StringUtils.isBlank(robotKey)) {
            throw new MessageException("sw-0001", "氢气参数不能为空: robotKey不能为空");
        }
    }
}
