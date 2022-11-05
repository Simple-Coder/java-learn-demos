package com.learn.boot.config;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.binder.jvm.ExecutorServiceMetrics;
import org.example.tp.demo.annotation.DynamicTp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.concurrent.*;

/**
 * Created by xiedong
 * Date: 2022/11/3 21:33
 */
@Configuration
public class ThreadConfig {

    /**
     * 通过{@link DynamicTp} 注解定义普通juc线程池，会享受到该框架监控功能，注解名称优先级高于方法名
     *
     * @return 线程池实例
     */
    @DynamicTp(value = "testtesttest",monitor = false)
    @Bean("xeidongommonExecutor1")
    public ExecutorService commonExecutor(MeterRegistry registry) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 1,
                60, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(1),
                Executors.defaultThreadFactory(),
                //拒绝策略：由调用线程处理
//                new ThreadPoolExecutor.CallerRunsPolicy()
                //拒绝策略：丢弃任务抛出reject异常
                new ThreadPoolExecutor.AbortPolicy()
        );
        return threadPoolExecutor;
//        return ExecutorServiceMetrics.monitor(registry, threadPoolExecutor, "xiedong", Collections.emptyList());

//        return (ThreadPoolExecutor) Executors.newFixedThreadPool(1);
    }
}
