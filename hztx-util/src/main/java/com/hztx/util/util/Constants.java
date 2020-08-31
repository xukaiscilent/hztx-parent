package com.hztx.util.util;


import com.hztx.util.json.Parser;
import com.hztx.util.json.SimpleParser;
import org.apache.commons.validator.routines.RegexValidator;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 常量
 */
public abstract class Constants {

    public static final String DEFAULT_DATE_FORMAT = "yyyy/MM/dd HH:mm";

    public static final String DEFAULT_DAY_FORMAT = "yyyy-MM-dd";

    public static final Parser SIMPLE_PARSER = new SimpleParser();

    public static final String JS_VERSION = "0.188";

    /**
     * 用户名格式验证
     */
    //public static final RegexValidator USERNAME_VALIDATOR = new RegexValidator("^[a-zA-Z0-9]{1}[a-zA-Z0-9|-|_]{2,14}[a-zA-Z0-9]{1}$");
    public static final RegexValidator USERNAME_VALIDATOR = new RegexValidator("^[\\u4E00-\\u9FA5A-Za-z0-9]+$");

    /**
     * 密码格式验证
     */
    public static final RegexValidator PASSWORD_VALIDATOR = new RegexValidator("^[0-9a-zA-Z]{6,16}$");

    public static final RegexValidator SIX_NUMBER = new RegexValidator("^\\d{6}$");


    /**
     * 邮箱格式验证
     */
    public static final RegexValidator EMAIL_VALIDATOR = new RegexValidator("^[A-Za-z0-9\\u4e00-\\u9fa5]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$");

    /**
     * 手机号格式验证
     *
     */
   public static final RegexValidator PHONE_VALIDATOR = new RegexValidator("^1[3,4,5,7,8,9]\\d{9}$");

    /**
     * 身份证格式验证
     */
   public static final RegexValidator IDCARD = new RegexValidator("/(^\\d{15}$)|(^\\d{18}$)|(^\\d{17}(\\d|X|x)$)/");

     /**
     * 车牌格式校验
     */
   public static final RegexValidator CAR_NUMBER = new RegexValidator("^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}[A-Z0-9]{4}[A-Z0-9挂学警港澳]{1}$");



   public static final RegexValidator PHONE_VALIDATOR_2017 = new RegexValidator("^(13[0-9]|14[579]|15[0-3,5-9]|17[0135678]|18[0-9])\\\\d{8}$");

    /**
     * 中文，英文，数字的组合，首位不能为数字，1-15位
     */
    public static final RegexValidator SYSTEM_NAME_VALIDATOR = new RegexValidator("^[a-zA-Z\u4e00-\u9fa5]{1}[a-zA-Z0-9\u4e00-\u9fa5]{1,14}$");

    /**
     * 中文，英文，数字的组合，1-15位
     */
    public static final RegexValidator SYSTEM_NAME_VALIDATOR2 = new RegexValidator("^[a-zA-Z0-9\u4e00-\u9fa5]{1,15}$");

    /**
     * 中文，英文，数字的组合，不能为纯数字，1-15位
     */
    public static final RegexValidator SYSTEM_NAME_VALIDATOR3 = new RegexValidator("^[[a-zA-Z0-9\u4e00-\u9fa5]*[a-zA-Z\u4e00-\u9fa5][a-zA-Z0-9\u4e00-\u9fa5]*]{1,15}$");

    /**
     * 纯中文验证
     */
    public static final RegexValidator CHINESE_STRING = new RegexValidator("^[\\u4E00-\\u9FFF]+$");


    public static String key="fbe47880b9171706";

    public static String getKey() {
        return key;
    }

    public static void setKey(String key) {
        Constants.key = key;
    }

    public static Date nullOrMorning(String date) {
        Date time = null;
        if (!StringUtil.isBlank(date)) {
            time = DateUtil.fromString(date, DEFAULT_DATE_FORMAT);
            time = DateUtil.getMorning(time);
        }
        return time;
    }

    public static Date nullOrNight(String date) {
        Date time = null;
        if (!StringUtil.isBlank(date)) {
            time = DateUtil.fromString(date, DEFAULT_DATE_FORMAT);
            time = DateUtil.getNight(time);
        }
        return time;
    }

    public static String nullOrNotBlank(String arg) {
        if (StringUtil.isBlank(arg))
            return null;
        else
            return arg;
    }

    public static String figureToString(Integer data){
        if(data==null)
            return "";
        else
            return data.toString();

    }


    public static String figureToString(BigDecimal data){
        if(data==null)
            return "";
        else
            return data.toString();

    }

    public static String nullString(String arg) {
        if (StringUtil.isBlank(arg))
            return "";
        else
            return arg;
    }


}
