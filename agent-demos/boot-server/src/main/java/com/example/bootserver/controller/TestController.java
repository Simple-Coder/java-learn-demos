package com.example.bootserver.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by xiedong
 * 2023/8/24 10:10
 */
@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping
    public Object test() {
        return "hi :" + System.currentTimeMillis();
    }

}
