package com.brucebat.message.common.message.wechat;

import lombok.Data;

import java.util.List;

/**
 * @author brucebat
 * @version 1.0
 * @since Created at 2021/5/26 11:42 下午
 */
@Data
public class Target {

    /**
     * 是否通知全部
     */
    private boolean atAll;

    /**
     * 用户id
     */
    private List<String> userIds;

    /**
     * 手机号
     */
    private List<String> phoneNumbers;
}
