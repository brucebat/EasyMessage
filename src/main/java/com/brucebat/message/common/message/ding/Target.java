package com.brucebat.message.common.message.ding;

import lombok.Data;

import java.util.List;

/**
 * 钉钉消息推送推送
 *
 * @author Sun Tianyu
 * @version 1.0
 * @since  Created in 2020/10/30
 */
@Data
public class Target {

    /**
     * 是否通知所有人
     */
    private boolean isAtAll;
    /**
     * 具体通知对象
     */
    private List<String> atMobiles;
}
