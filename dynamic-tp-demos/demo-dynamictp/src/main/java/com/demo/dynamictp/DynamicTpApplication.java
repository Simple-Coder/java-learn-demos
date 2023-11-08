package com.demo.dynamictp;

import org.dromara.dynamictp.core.spring.EnableDynamicTp;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by xiedong
 * 2023/11/8
 */
@SpringBootApplication
@EnableDynamicTp
public class DynamicTpApplication {
    public static void main(String[] args) {
        SpringApplication.run(DynamicTpApplication.class, args);
    }
}
