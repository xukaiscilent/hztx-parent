/*
package com.hztx.util.minio;

import io.minio.MinioClient;
import io.minio.ObjectStat;
import io.minio.PutObjectOptions;
import io.minio.messages.Bucket;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

*/
/**
 * @auther xukai
 * @date 2020/8/20.
 *//*


@RequiredArgsConstructor
public class MinioTemplate implements InitializingBean {


    private final String endpoint;
    private final String accessKey;
    private final String secretKey;
    private MinioClient client;

    */
/**
     * 创建bucket
     *
     * @param bucketName bucket名称
     *//*

    @SneakyThrows
    public void createBucket(String bucketName) {
        if (!client.bucketExists(bucketName)) {
            client.makeBucket(bucketName);
        }
    }

    */
/**
     * 获取全部bucket
     * <p>
     * https://docs.minio.io/cn/java-client-api-reference.html#listBuckets
     *//*

    @SneakyThrows
    public List<Bucket> getAllBuckets() {
        return client.listBuckets();
    }

    */
/**
     * 根据bucketName获取信息
     * @param bucketName bucket名称
     *//*

    @SneakyThrows
    public Optional<Bucket> getBucket(String bucketName) {
        return client.listBuckets().stream().filter(b -> b.name().equals(bucketName)).findFirst();
    }

    */
/**
     * 根据bucketName删除信息
     * @param bucketName bucket名称
     *//*

    @SneakyThrows
    public void removeBucket(String bucketName) {
        client.removeBucket(bucketName);
    }



    */
/**
     * 获取文件外链
     *
     * @param bucketName bucket名称
     * @param objectName 文件名称
     * @param expires    过期时间 <=7
     * @return url
     *//*

    @SneakyThrows
    public String getObjectURL(String bucketName, String objectName, Integer expires) {
        return client.presignedGetObject(bucketName, objectName, expires);
    }

    */
/**
     * 获取文件
     *
     * @param bucketName bucket名称
     * @param objectName 文件名称
     * @return 二进制流
     *//*

    @SneakyThrows
    public InputStream getObject(String bucketName, String objectName) {
        return client.getObject(bucketName, objectName);
    }


    */
/**
     * 上传文件
     *
     * @param bucketName  bucket名称
     * @param objectName  文件名称
     * @param stream      文件流
     * @param contextType 类型
     * @throws Exception https://docs.minio.io/cn/java-client-api-reference.html#putObject
     *//*

    public void putObject(String bucketName, String objectName, InputStream stream, String contextType) throws Exception {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", contextType);
        PutObjectOptions putObjectOptions=new PutObjectOptions(stream.available(),-1);
        putObjectOptions.setHeaders(headers);
        client.putObject(bucketName,objectName,stream,putObjectOptions);
        //client.putObject(bucketName, objectName, stream, size, contextType);
    }

    */
/**
     * 获取文件信息
     *
     * @param bucketName bucket名称
     * @param objectName 文件名称
     * @throws Exception https://docs.minio.io/cn/java-client-api-reference.html#statObject
     *//*

    public ObjectStat getObjectInfo(String bucketName, String objectName) throws Exception {
        return client.statObject(bucketName, objectName);
    }

    */
/**
     * 删除文件
     *
     * @param bucketName bucket名称
     * @param objectName 文件名称
     * @throws Exception https://docs.minio.io/cn/java-client-api-reference.html#removeObject
     *//*

    public void removeObject(String bucketName, String objectName) throws Exception {
        client.removeObject(bucketName, objectName);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.hasText(endpoint, "Minio url 为空");
        Assert.hasText(accessKey, "Minio accessKey为空");
        Assert.hasText(secretKey, "Minio secretKey为空");
        this.client = new MinioClient(endpoint, accessKey, secretKey);
    }

    public static void main(String[] args) throws Exception {
        String endpoint="http://47.110.71.246:9000";
        String accessKey="hztx";
        String secreatKey="hztx@20200820";
        MinioTemplate template=new MinioTemplate(endpoint,accessKey,secreatKey);
        template.afterPropertiesSet();
        List<Bucket> buckets=template.getAllBuckets();
        System.out.println("fga");



    }


}
*/
