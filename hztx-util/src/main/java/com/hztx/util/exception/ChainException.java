package com.hztx.util.exception;


import com.hztx.util.web.ResultCode;

public class ChainException extends RuntimeException {

    private ResultCode errorCode;

    private String msg;

    public ChainException() {
        this(ResultCode.FAILURE);
    }

    public ChainException(ResultCode errorCode) {
        super(errorCode.getDesc());
        this.errorCode = errorCode;
        this.msg = errorCode.getDesc();
    }

    public ChainException(ResultCode errorCode, String msg) {
        super(msg);
        this.errorCode = errorCode;
        this.msg = msg;
    }

    public ResultCode getErrorCode() {
        return errorCode;
    }

    public String getMsg() {
        return msg;
    }
}
