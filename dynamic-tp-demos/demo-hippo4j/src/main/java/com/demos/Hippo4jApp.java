package com.demos;

import cn.hippo4j.core.enable.EnableDynamicThreadPool;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

/**
 * Created by xiedong
 * Date: 2023/11/7 21:31
 */
@SpringBootApplication
@EnableDynamicThreadPool
@EnableHystrix
public class Hippo4jApp {
    public static void main(String[] args) {

        SpringApplication.run(Hippo4jApp.class, args);
    }
}
