package com.brucebat.message.common.util;

import org.apache.commons.lang3.StringUtils;

/**
 * 邮箱工具类
 *
 * @author Sun Tianyu
 * @version 1.0
 * @since Created in 2021/2/7
 */
public class MailUtil {

    /**
     * 邮箱地址数量（按照"@"进行切分）
     */
    private static final int MAIL_ADDRESS_PART_NUMBER = 2;

    /**
     * 邮箱地址正则表达式
     */
    private static final String MAIL_ADDRESS_REGEX = "^[A-Za-z\\d]+([-_.][A-Za-z\\d]+)*@([A-Za-z\\d]+[-.])+[A-Za-z\\d]{2,4}$";

    /**
     * 获取邮箱host
     *
     * @param mailAddress 邮箱地址
     * @return 邮箱host地址
     */
    public static String getMailHost(String mailAddress) {
        if (StringUtils.isBlank(mailAddress)) {
            return null;
        }
        String[] parts = mailAddress.split("@");
        if (parts.length != MAIL_ADDRESS_PART_NUMBER) {
            return null;
        }
        return parts[1];
    }

    /**
     * 进行邮箱地址检查
     *
     * @param mailAddress 邮箱地址
     * @return 是否为邮箱地址
     */
    public static boolean checkAddress(String mailAddress) {
        if (StringUtils.isBlank(mailAddress)) {
            return false;
        }
        return mailAddress.matches(MAIL_ADDRESS_REGEX);
    }
}
