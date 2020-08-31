package com.hztx.file.api;

import com.alibaba.fastjson.JSONObject;
import com.hztx.file.response.UploadResponse;
import com.hztx.util.exception.UserOperationException;
import com.hztx.util.minio.MiniUtil;

import com.hztx.util.web.ApiResponse;
import com.hztx.util.web.ApiResponseUtils;
import io.swagger.annotations.Api;

import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * @auther xukai
 * @date 2020/8/25.
 */
@Controller
@RequestMapping(value = "file", produces = "application/json;charset=utf-8")
@Api("文件控制服务器")
public class FileController {

    private Logger logger = LoggerFactory.getLogger(FileController.class);

    private static final String endPoint = "http://47.110.71.246:9000";
    private static final String accessKey = "hztx";
    private static final String secreatKey = "hztx@20200820";


    private static String checkImage(MultipartFile file) {
        String contentType = file.getContentType();
        String fileType = contentType.substring(0, contentType.indexOf("/"));
        if (!fileType.equals("image")) throw new UserOperationException("图片类型不对");
        //获得文件后缀名称
        String imageName = contentType.substring(contentType.indexOf("/") + 1);
        return imageName;
    }

    @ApiOperation("上传单张图片")
    @RequestMapping(value = "/uploadPictures", method = RequestMethod.POST)
    @ResponseBody
public ApiResponse<UploadResponse> uploadPicture(@RequestParam("file") MultipartFile file) {
        UploadResponse response = null;
        String suffix=checkImage(file);
        try {
           JSONObject object = MiniUtil.uploadImage(file.getInputStream(), suffix);
           response.setImageUrl((String) object.get("url"));
        } catch (Exception e) {
            logger.info("minio上传文件失败");
            logger.error("minio上传失败",e);
            return ApiResponseUtils.failure();
        }
        return ApiResponseUtils.success(response);
    }

    @ApiOperation("上传单个文件")
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public ApiResponse uploadFile(@RequestParam("file") MultipartFile file) {

        return ApiResponseUtils.success();
    }


}
