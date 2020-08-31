package com.hztx.util.web;

/**
 * 错误码。
 */
public enum ErrorCode {

    ERROR_SYSTEM(000001,"系统异常"),
    /**登录异常**/
    NOT_LOGIN(100001, "用户未登陆"),
    USER_EXIST(100002, "用户已存在"),
    LOGIN_EXPIRED(100003, "登陆过期"),
    LOGIN_INVALID(100004, "登陆失效"),
    CAPTCHA_ERROR(100005, "验证码错误"),
    USER_OPERATION_ERROR(100006, "用户操作异常"),
    ACCOUNT_FREEZE(100007,"账号冻结"),
    STOP_SPEAKING(100008,"禁言"),

    /**其它异常**/
    DATA_NOT_FOUND(400001, "找不到记录"),
    SERVER_ERROR(400002, "服务器内部错误"),
    ILLEGAL_ARGUMENT(400003, "非法参数"),
    STATE_ERROR(400004, "状态错误"),
    ALREADY_EXISTS(400005, "内容重复"),
    NOT_FOUND(400006, "找不到内容"),
    /**权限异常**/
    ACCESS_DENIED(500001, "无权限访问"),
    AUTHENTICATE_FAILURE(500002, "验证失败"),
    USER_LEVEL(500003, "用户等级不够"),
    ERROR_PARAMETER(400002,"参数校验异常");








    private final int code;

    private final String text;

    ErrorCode(int code, String text) {
        this.text = text;
        this.code = code;
    }

    public String getText() {
        return text;
    }

    public int getCode() {
        return code;
    }
}
