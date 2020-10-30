package com.brucebat.message.common.message.ding;

import lombok.Data;

import java.util.List;

/**
 * @author Sun Tianyu
 * @version 1.0
 * @date Created in 2020/10/30
 * @description
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
