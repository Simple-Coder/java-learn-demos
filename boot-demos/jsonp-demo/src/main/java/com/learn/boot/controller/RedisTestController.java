package com.learn.boot.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by xiedong
 * Date: 2023/7/18 22:20
 */
@RequestMapping
@Slf4j
@RestController("/redis/test")
public class RedisTestController {

    @Resource
    private RedisTemplate redisTemplate;


    @GetMapping("/t1")
    public Object get(){
        return 1;
    }
}
