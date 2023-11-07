package com.demos.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by xiedong
 * Date: 2023/11/7 21:35
 */
@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {
    @Resource
    private ThreadPoolExecutor messageConsumeDynamicExecutor;

    @GetMapping("/test")
    public Object test1() {
        messageConsumeDynamicExecutor.execute(() -> {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("sleep end...");
                }
        );
        return "success";
    }
}
