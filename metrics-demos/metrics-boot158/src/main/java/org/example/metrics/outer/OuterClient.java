package org.example.metrics.outer;

import cn.hutool.http.HttpException;
import cn.hutool.http.HttpUtil;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.example.metrics.aspect.HystrixFallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Created by dongxie on 2022/8/5.
 */
@Component
@Slf4j
@DefaultProperties(
        commandProperties = {
                @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000"),
                //请求的失败数目超过这个之后，就会打开熔断器
                @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
                //熔断器工作时间，默认5秒，超过这个时间便会放流量进去
                @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "3000"),
//                @HystrixProperty(name = "circuitBreaker.forceOpen", value = "true"),
                //出错率超过75%，启动熔断器，默认50%
                @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "75")
        },
        threadPoolProperties = {
                //核心线程数
                @HystrixProperty(name = "coreSize", value = "10"),
                //最大核心线程数
                @HystrixProperty(name = "maximumSize", value = "150"),
                //超过队列的个数会直接失败
                @HystrixProperty(name = "maxQueueSize", value = "150"),
                //队列小于最大值，拒绝请求
                @HystrixProperty(name = "queueSizeRejectionThreshold", value = "150")
        })
public class OuterClient {
    @Autowired
    private RestTemplate restTemplate;
    @Value("${server.port}")
    private Long serverPort;

    @HystrixCommand(
            commandKey = "getHystrixTestCommand",
            fallbackMethod = "getHystrixTestFallback",
            groupKey = "getHystrixTestGroup",
            threadPoolKey = "getHystrixTestPool"
    )
    public Object getHystrixTest(int num) {
        String forObject = restTemplate.getForObject("http://127.0.0.1:" + serverPort + "/hystrix/test1?num=" + num, String.class);
//        String forObject = restTemplate.getForObject("http://127.0.0.1:8080/hystrix/test1?num=" + num, String.class);
        return forObject + restTemplate.toString();
    }

    @SuppressWarnings("unused")
    public Object getHystrixTestFallback(int num, Throwable e) {
//        log.error("getHystrixTestFallback errOr", e);
        log.error("getHystrixTestFallback errOr", e);
        return "getHystrixTestFallback error";
    }


    @HystrixCommand(
            fallbackMethod = "getAFallback",
            groupKey = "getAGroup",
            threadPoolKey = "getAPool"
    )
    public String getA() {
        try {
            return HttpUtil.createGet("https://www.baidu.com").timeout(1).execute().body();
        } catch (Exception e) {
            log.info("getA方法");
            return null;
        }
    }

    @SuppressWarnings("unused")
    public String getAFallback(Throwable e) {
        log.error("getAFallback 执行降级方法");
        return null;
    }


    @HystrixCommand(
            fallbackMethod = "getBFallback",
            groupKey = "getBGroup",
            threadPoolKey = "getBPool"
    )
    public String getB() {
        try {
            return HttpUtil.createGet("https://www.baidu.com").timeout(100000).execute().body();
        } catch (Exception e) {
            log.info("getB方法");
            return null;
        }
    }

    @SuppressWarnings("unused")
    public String getBFallback(Throwable e) {
        log.error("getBFallback 执行降级方法");
        return null;
    }


}
