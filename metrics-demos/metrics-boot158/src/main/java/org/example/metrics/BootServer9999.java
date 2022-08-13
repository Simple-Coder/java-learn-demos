package org.example.metrics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

/**
 * Created by xiedong
 * Date: 2022/8/13 16:19
 */
@SpringBootApplication
@EnableHystrix
@EnableDiscoveryClient
public class BootServer9999 {
    public static void main(String[] args) {
        SpringApplication.run(BootServer9999.class, args);
    }
}
