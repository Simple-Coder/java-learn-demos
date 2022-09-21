package metrics.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by xiedong
 * Date: 2022/9/21 22:09
 */
@Configuration
public class ThreadConfig {
    @Bean("globalTPExecutor")
    public ThreadPoolExecutor globalTPExecutor() {
        return new ThreadPoolExecutor(10, 10,
                60, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(300),
                Executors.defaultThreadFactory(),
                //拒绝策略：由调用线程处理
//                new ThreadPoolExecutor.CallerRunsPolicy()
                //拒绝策略：丢弃任务抛出reject异常
                new ThreadPoolExecutor.AbortPolicy()
        );
    }
}
