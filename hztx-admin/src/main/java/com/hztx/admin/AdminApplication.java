package com.hztx.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @auther xukai
 * @date 2020/8/24.
 */
@SpringBootApplication
@EnableAutoConfiguration
@MapperScan(basePackages = "com.hztx.core.mapper.*")
public class AdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class);
    }
}
