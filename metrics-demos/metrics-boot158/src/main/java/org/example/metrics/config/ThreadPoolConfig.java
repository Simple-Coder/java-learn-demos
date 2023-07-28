package org.example.metrics.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.*;

/**
 * Created by xiedong
 * Date: 2023/7/8 22:48
 */
@Configurable
public class ThreadPoolConfig {
    @Bean("feedWebGlobalTpExecutor")
    public ExecutorService globalTPExecutor() {
        return new ThreadPoolExecutor(2, 2,
                60, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(300),
                Executors.defaultThreadFactory(),
                //拒绝策略：由调用线程处理
//                new ThreadPoolExecutor.CallerRunsPolicy()
                //拒绝策略：丢弃任务抛出reject异常
                new ThreadPoolExecutor.AbortPolicy()
        );
    }


    @Bean("feedExtraThreadPool")
    public ExecutorService feedExtraThreadPool() {
        return new ThreadPoolExecutor(2, 2,
                60, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(300),
                Executors.defaultThreadFactory(),
                //拒绝策略：由调用线程处理
                new ThreadPoolExecutor.CallerRunsPolicy()
                //拒绝策略：丢弃任务抛出reject异常
//                new ThreadPoolExecutor.AbortPolicy()
        );
    }
}
