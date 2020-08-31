package com.hztx.util.web;




public class ApiResponseUtils {

    public static <T> ApiResponse<T> response(String resultCode, String resultMsg, T data) {
        return new ApiResponse<>(resultCode, resultMsg, data);
    }

    public static <T> ApiResponse<T> response(ResultCode resultCode, T data) {
        return response(resultCode.getCode(), resultCode.getDesc(), data);
    }

    public static <T> ApiResponse<T> response(ResultCode resultCode) {
        return response(resultCode.getCode(), resultCode.getDesc(), null);
    }

    public static <T> ApiResponse<T> success(T data) {
        return response(ResultCode.SUCCESS, data);
    }

    public static <T> ApiResponse<T> success() {
        return success(null);
    }

    public static <T> ApiResponse<T> failure(String msg) {
        return response(ResultCode.FAILURE.getCode(), msg, null);
    }

    public static <T> ApiResponse<T> failure() {
        return response(ResultCode.FAILURE.getCode(), ResultCode.FAILURE.getDesc(), null);
    }
    public static <T> ApiResponse<T> failure(ResultCode code,String msg) {
        return response(code.getCode(),msg, null);
    }
}
