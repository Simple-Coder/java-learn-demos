package org.example.metrics.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.metrics.config.SystemVariableService;
import org.example.metrics.outer.OuterClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by dongxie on 2022/8/5.
 */
@RequestMapping("/hystrix")
@RestController
@Slf4j
public class HystrixTestController {
    @Autowired
    private OuterClient outerClient;

    @Autowired
    private SystemVariableService systemVariableService;

    // http://127.0.0.1:8989/hystrix/test?num=12
    @GetMapping("/test")
    public Object test(@RequestParam int num) {

//        systemVariableService.putSystemConfigValueByKey("circuitForceFlag", "" + (num % 2));
//        if (num % 2 == 0) {
//            System.setProperty("hystrix.command.getHystrixTestCommand.circuitBreaker.forceOpen","true");
//        } else {
//            System.setProperty("hystrix.command.getHystrixTestCommand.circuitBreaker.forceOpen","false");
//        }
        return outerClient.getHystrixTest(num);
    }

    @GetMapping("/test1")
    // http://127.0.0.1:8989/hystrix/test1?num=12
    public Object test1(@RequestParam int num) {
        try {
            Thread.sleep(num);
//        if (num % 10 == 0) {
//            throw new RuntimeException("num % 10 ==0");
//        }
            return Thread.currentThread().getName();
        } catch (InterruptedException e) {
            return "timout error";
        }
    }


}
