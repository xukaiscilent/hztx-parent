package com.hztx.util.exception;


import com.hztx.util.web.ResultCode;

/**
 * Created by xukai on 2017/8/1.
 */
public class UserOperationException extends RuntimeException {

    private String message="用户操作异常";

    private Object object;

    private String resultCode;

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public UserOperationException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserOperationException(ResultCode resultCode, String message) {
        super(message);
        this.resultCode=resultCode.getCode();
    }
    public UserOperationException(Throwable cause) {
        super(cause);
    }

    public Object getObject() {
        return object;
    }
    public void setObject(Object object) {
        this.object = object;
    }
    public UserOperationException(String message) {
        this.message = message;
    }

    public UserOperationException() {
        super();
    }


}
