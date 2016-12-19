package com.andy.baselibrary.utils;

import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Andy on 2016/12/19.
 */

public class RegularUtils {

    public static boolean isIp(String ipAddress) {
        String ip = "^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\."
                + "(00?\\d|1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
                + "(00?\\d|1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
                + "(00?\\d|1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$";

        Pattern pattern = Pattern.compile(ip);
        Matcher matcher = pattern.matcher(ipAddress);
        boolean flag = matcher.matches();
        return flag;
    }

    public static boolean isPortAvailable(String portValue) {
        try {
            int portInt = Integer.valueOf(portValue).intValue();
            if (portInt == 80 || (1025 <= portInt && portInt <= 65535)) {
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * 是否手机号.
     *
     * @param text cellphone
     * @return
     */
    public static boolean isCellPhone(String text) {
        return isMatch(text, "^(\\+?86)?(1[34578]\\d{9})$");
    }

    public static boolean isMatch(String text, String regularExpression) {
        if (!TextUtils.isEmpty(text)) {
            if (text.matches(regularExpression)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 是否座机号
     *
     * @param text cellphone
     * @return
     */
    public static boolean isPhone(String text)
    {
        return isMatch(text, "^0(10|2[012345789]|[3-9]\\d{2})-\\d{7,8}(-\\d{1,6})?$");
    }

    /**
     * 判断文本是否全汉字.
     *
     * @param text
     * @return
     */
    public static boolean isChinese(String text)
    {
        return isMatch(text, "[\u4e00-\u9fa5]*");
    }

    public static boolean isEmail(String text)
    {
        return isMatch(text, "[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?");
    }
}
