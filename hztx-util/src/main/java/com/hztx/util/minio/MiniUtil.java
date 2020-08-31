package com.hztx.util.minio;

import com.alibaba.fastjson.JSONObject;
import com.hztx.util.util.StringUtil;
import io.minio.MinioClient;
import io.minio.policy.PolicyType;


import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @auther xukai
 * @date 2020/8/25.
 */

public class MiniUtil {

    private static String minio_url="http://47.110.71.246:9000";
    private static String minio_name="hztx";

    private static String minio_pass="hztx@20200820";



    /**
     * @param inputStream
     * @param suffix
     * @return
     * @throws Exception
     * @Title: uploadImage
     * @Description:上传图片
     */
    public static JSONObject uploadImage(InputStream inputStream, String suffix) throws Exception {
        return upload("image",inputStream, suffix, "image/jpeg");
    }


    /**
     * @param inputStream
     * @param suffix
     * @return
     * @throws Exception
     * @Title: uploadVideo
     * @Description:上传视频
     */
    public static JSONObject uploadVideo(InputStream inputStream, String suffix) throws Exception {
        return upload("video",inputStream, suffix, "video/mp4");
    }


    /**
     * @param inputStream
     * @param suffix
     * @return
     * @throws Exception
     * @Title: uploadVideo
     * @Description:上传文件
     */
    public static JSONObject uploadFile(InputStream inputStream, String suffix) throws Exception {
        return upload("file",inputStream, suffix, "application/octet-stream");
    }


    /**
     * 上传字符串大文本内容
     *
     * @param str
     * @return
     * @throws Exception
     * @Title: uploadString
     * @Description:描述方法
     */
    public static JSONObject uploadString(String str) throws Exception {
        if (StringUtil.isBlank(str)) {
            return new JSONObject();
        }
        InputStream inputStream = new ByteArrayInputStream(str.getBytes());
        return upload("bigText",inputStream, null, "text/html");
    }

    private static final String bucketPublicPolicy = "{\"Version\":\"2012-10-17\",\"Statement\":[{\"Action\":[\"s3:GetBucketLocation\",\"s3:ListBucket\",\"s3:ListBucketMultipartUploads\"],\"Effect\":\"Allow\",\"Principal\":{\"AWS\":[\"*\"]},\"Resource\":[\"arn:aws:s3:::test\"],\"Sid\":\"\"},{\"Action\":[\"s3:AbortMultipartUpload\",\"s3:DeleteObject\",\"s3:GetObject\",\"s3:ListMultipartUploadParts\",\"s3:PutObject\"],\"Effect\":\"Allow\",\"Principal\":{\"AWS\":[\"*\"]},\"Resource\":[\"arn:aws:s3:::test/*\"],\"Sid\":\"\"}]}";


    /**
     * @return
     * @throws Exception
     * @Title: upload
     * @Description:上传主功能
     */
    private static JSONObject upload(String bucketName,InputStream inputStream, String suffix, String contentType) throws Exception {
        JSONObject map = new JSONObject();


        MinioClient minioClient = new MinioClient(minio_url, minio_name, minio_pass);

        if (!minioClient.bucketExists(bucketName)) {
            minioClient.makeBucket(bucketName);
            minioClient.setBucketPolicy(bucketName,"*", PolicyType.READ_ONLY);
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String ymd = sdf.format(new Date());
        String objectName = ymd + "/" + UUID.randomUUID().toString() + "."+(suffix != null ? suffix : "");


        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", contentType);
      /*  PutObjectOptions putObjectOptions = new PutObjectOptions(inputStream.available(), -1);
        putObjectOptions.setHeaders(headers);*/
        minioClient.putObject(bucketName, objectName, inputStream, contentType);

       // minioClient.putObject(bucketName, objectName, inputStream, putObjectOptions);
        String url = minioClient.getObjectUrl(bucketName, objectName);
        map.put("flag", "0");
        map.put("url", url);
        map.put("urlval", url);
        map.put("path", bucketName + "/" + objectName);
        return map;
    }

}
