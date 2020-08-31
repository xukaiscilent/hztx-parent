package com.hztx.admin.exceptionHandler;


import com.hztx.util.exception.ChainException;
import com.hztx.util.exception.UserOperationException;
import com.hztx.util.web.ApiResponse;
import com.hztx.util.web.ApiResponseUtils;
import com.hztx.util.web.ErrorCode;
import com.hztx.util.web.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;

/**
 * Created by xukai on 2017/8/1.
 */
@ControllerAdvice
public class ExceptionHandlerAdvice {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandlerAdvice.class);

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ApiResponse handlerException(Exception ex) {
        LOGGER.error("错误",ex);
        return ApiResponseUtils.failure(ResultCode.FAILURE.getDesc());
    }

    @ExceptionHandler(ChainException.class)
    @ResponseBody
    public ApiResponse handlerException(ChainException ex) {
        LOGGER.error("错误",ex);
        return ApiResponseUtils.failure(ex.getErrorCode(),ex.getMessage());
    }


    @ExceptionHandler(UserOperationException.class)
    @ResponseBody
    public ApiResponse handlerUserNotLoginException(UserOperationException ex) {
        LOGGER.error("用户操作异常",ex);
        return ApiResponseUtils.failure(ex.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    public ApiResponse handleIllegalArgumentException(IllegalArgumentException ex) {
        LOGGER.error("非法参数",ex);
        return ApiResponseUtils.failure(ex.getMessage());
    }

    @ExceptionHandler(SQLException.class)
    @ResponseBody
    public ApiResponse handleSqlException(SQLException ex) {
        LOGGER.error("sql异常",ex);
        return ApiResponseUtils.failure(ErrorCode.SERVER_ERROR.getText());
    }











}
