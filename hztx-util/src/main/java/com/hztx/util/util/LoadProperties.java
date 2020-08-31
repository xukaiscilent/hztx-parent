package com.hztx.util.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by xukai on 2017/8/22.
 */
public class LoadProperties {

    /**
     * 通过key值获取配置文件中value值
     * @description 方法描述
     * @param key 配置文件中key值
     */
    public static String readValue(String key) {
       return readFileValue("/sms.properties",key);
    }




    public static String readFileValue(String fileName, String key) {
        Properties props = new Properties();
        InputStream in = null;
        String value = "";
        try {
            in = LoadProperties.class.getResourceAsStream(fileName);
            props.load(in);
            value = props.getProperty(key);
            return value;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return value;
    }
}
