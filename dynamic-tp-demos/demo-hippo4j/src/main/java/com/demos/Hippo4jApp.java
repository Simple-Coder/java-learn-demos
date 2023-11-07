package com.demos;

import cn.hippo4j.core.enable.EnableDynamicThreadPool;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by xiedong
 * Date: 2023/11/7 21:31
 */
@SpringBootApplication
@EnableDynamicThreadPool
public class Hippo4jApp {
    public static void main(String[] args) {

        SpringApplication.run(Hippo4jApp.class, args);
    }
}
