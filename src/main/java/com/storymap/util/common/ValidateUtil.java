package com.storymap.util.common;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

/**
 * @description: desc
 * @author: wdf
 * @email: wdf.coder@gmail.com
 * @date: 2021/1/16 17:15
 */
@Component
public class ValidateUtil {
    private static final String REGEX_MOBILE = "((\\+86|0086)?\\s*)((134[0-8]\\d{7})|(((13([0-3]|[5-9]))|(14[5-9])|15([0-3]|[5-9])|(16(2|[5-7]))|17([0-3]|[5-8])|18[0-9]|19(1|[8-9]))\\d{8})|(14(0|1|4)0\\d{7})|(1740([0-5]|[6-9]|[10-12])\\d{7}))";
    private static final String REGEX_EMAIL ="[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?";
    public boolean isPhoneNumber(String tel){
        if(StringUtils.isEmpty(tel))
            return false;

        return Pattern.matches(REGEX_MOBILE,tel);
    }


    public boolean isEmail(String email) {
        if(StringUtils.isEmpty(email))
            return false;
        return Pattern.matches(REGEX_EMAIL,email);
    }
}
