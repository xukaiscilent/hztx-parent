package com.hztx.util.web;

/**
 * 返回码。
 */
public enum ResultCode {

    SUCCESS("000000", "成功"),
    FAILURE("999999", "失败"),

    /*用户相关错误码*/
    USER_NOT_EXIST("100001", "用户不存在"),
    USER_NOT_LOGIN("100002", "用户未登录"),
    USER_WRONG_PASSWORD("100003", "密码错误"),
    CAPTCHA_ERROR("100008", "验证码错误"),
    PHONE_EXIT("100009","手机号已注册"),

    PROJECT_COLLECT_EARLY("100004", "项目已经收藏"),
    PROJECT_NOT_EXIT("100005","项目不存在"),
    PROJECT_RECOMMEND_EARLY("100006","已经推荐过该项目"),
    UPLOAD_PICTURES_FAILURE("100007","图片上传失败");

    ResultCode(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private String code;

    private String desc;

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
