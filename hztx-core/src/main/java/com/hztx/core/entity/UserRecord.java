package com.hztx.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
* <p>
    * 
    * </p>
*
* @author xukai
* @since 2020-08-25
*/
    @Data
        @EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    public class UserRecord implements Serializable {

    private static final long serialVersionUID = 1L;

            @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

            /**
            * 用户id
            */
    private Integer userId;

            /**
            * 设备id
            */
    private Integer deviceId;

            /**
            * 小区id
            */
    private Integer villageId;

    private LocalDateTime createTime;

            /**
            * 设备唯一标志
            */
    private String deviceSn;

    private Integer carId;

            /**
            * 通行方式1-人脸 2 车牌
            */
    private Integer type;

            /**
            * 通行用户类型 0--用户 1--访客
            */
    private Integer userType;

            /**
            * 进出方式0 --人脸 1--二维码
            */
    private Integer walkType;


}
