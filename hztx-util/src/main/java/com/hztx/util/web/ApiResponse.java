package com.hztx.util.web;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by xukai on 2020/3/20.
 */

@ApiModel("接口响应数据结构")
public class ApiResponse<T> {

    @ApiModelProperty(
            value = "状态码",
            required = true
    )
    private String response;
    @ApiModelProperty("响应消息")
    private String message;
    @ApiModelProperty("响应结果数据")
    private T data;

    public ApiResponse() {
    }

    public ApiResponse(String response, String message, T data) {
        this.response = response;
        this.message = message;
        this.data = data;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
