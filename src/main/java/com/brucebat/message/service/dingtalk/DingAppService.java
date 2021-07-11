package com.brucebat.message.service.dingtalk;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.brucebat.message.common.config.DingAppProperties;
import com.brucebat.message.common.enums.DingApiEnum;
import com.brucebat.message.common.enums.DingResponseEnum;
import com.brucebat.message.common.exception.MessageException;
import com.brucebat.message.common.util.HttpUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 钉钉应用服务类
 *
 * @author brucebat
 * @version 1.0
 * @since Created at 2021/4/22 11:02 上午
 */
public class DingAppService {

    private static final Logger log = LoggerFactory.getLogger(DingAppService.class);

    private final DingAppProperties dingAppProperties;

    public DingAppService(DingAppProperties dingAppProperties) {
        this.dingAppProperties = dingAppProperties;
    }

    /**
     * 根据本地配置的钉钉应用配置信息获取对应的应用accessToken
     *
     * @return 获取应用的调用accessToken
     * @throws MessageException 消息异常
     */
    public String getAccessToken() {
        return this.getAccessToken(dingAppProperties.getAppKey(), dingAppProperties.getAppSecret());
    }

    /**
     * 获取调用API的令牌
     *
     * @param appKey    钉钉应用key
     * @param appSecret 钉钉应用secret
     * @return 调用令牌
     * @throws MessageException 消息异常
     */
    public String getAccessToken(String appKey, String appSecret) {
        if (StringUtils.isBlank(appKey) || StringUtils.isBlank(appSecret)) {
            throw new MessageException("sw-0001", "请求参数不能为空 : appKey或appSecret为空");
        }
        String url = String.format(DingApiEnum.GET_ACCESS_TOKEN.getApiPath(), appKey, appSecret);
        String response = HttpUtil.get(url);
        if (StringUtils.isBlank(response)) {
            throw new MessageException("sw-0010", "获取钉钉应用accessToken异常: 返回结果为空");
        }
        JSONObject result = JSON.parseObject(response);
        if (0 != result.getIntValue(DingResponseEnum.ERROR_CODE.getFiled())) {
            throw new MessageException("sw-0010", "获取钉钉应用accessToken异常: " + result.getString(DingResponseEnum.ERROR_MSG.getFiled()));
        }
        return result.getString(DingResponseEnum.ACCESS_TOKEN.getFiled());
    }

    /**
     * 根据手机号获取用户id
     *
     * @param accessToken 调用接口令牌
     * @param phone 手机号
     * @return 用户id
     * @throws MessageException 消息异常
     */
    public String getUserIdByPhone(String accessToken, String phone) {
        if (StringUtils.isBlank(accessToken) || StringUtils.isBlank(phone)) {
            throw new MessageException("sw-0001", "请求参数不能为空 : accessToken或phone为空");
        }
        String url = String.format(DingApiEnum.GET_USER_ID_BY_PHONE.getApiPath(), accessToken);
        JSONObject request = new JSONObject().fluentPut("mobile", phone);
        String response = HttpUtil.post(url, request);
        if (StringUtils.isBlank(response)) {
            throw new MessageException("sw-0010", "根据手机获取用户id异常: 返回结果为空");
        }
        JSONObject result = JSON.parseObject(response);
        if (0 != result.getIntValue(DingResponseEnum.ERROR_CODE.getFiled())) {
            throw new MessageException("sw-0010", "根据手机获取用户id异常: " + result.getString(DingResponseEnum.ERROR_MSG.getFiled()));
        }
        return result.getJSONObject(DingResponseEnum.RESULT.getFiled()).getString("userid");
    }
}
