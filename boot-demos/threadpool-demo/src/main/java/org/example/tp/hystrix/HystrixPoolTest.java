package org.example.tp.hystrix;

import com.netflix.hystrix.*;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * Created by dongxie on 2022/12/29.
 */
public class HystrixPoolTest {
    @SneakyThrows
    public static void main(String[] args) {
        // 变量
        final int coreSize = 5, maximumSize = 10, maxQueueSize = 5, rejThresholdSize = 6;
        HystrixCommand.Setter commandConfig = generateCommandConfig(coreSize, maximumSize, maxQueueSize, rejThresholdSize);

        // Run command once, so we can get metrics.
        runOnce(commandConfig);

        // 模拟并发
        final CountDownLatch stopLatch = new CountDownLatch(1);
        List<Thread> threads = new ArrayList<Thread>();
        for (int i = 0; i < coreSize + maximumSize + maxQueueSize + rejThresholdSize; i++) {
            final int fi = i + 1;
            String threadName = "TestThread-" + fi;
            Thread thread = new Thread(() -> {
                try {
                    HystrixCommand<Void> command = new HystrixCommand<Void>(commandConfig) {
                        @Override
                        protected Void run() throws Exception {
                            stopLatch.await();
                            return null;
                        }
                    };
                    command.execute();
                } catch (Exception e) {
                    System.out.println("Thread:" + threadName + " got rejected.");
                    System.out.println();
                }
            });
            thread.setName(threadName);
            threads.add(thread);
            thread.start();
            Thread.sleep(200);

            System.out.println("start:" + threadName);
            printThreadPoolStatus();
        }

        // 线程执行释放
        stopLatch.countDown();
        for (Thread thread : threads) {
            thread.join();
        }
    }

    static void printThreadPoolStatus() {
        for (HystrixThreadPoolMetrics threadPoolMetrics : HystrixThreadPoolMetrics.getInstances()) {
            String name = threadPoolMetrics.getThreadPoolKey().name();
            Number poolSize = threadPoolMetrics.getCurrentPoolSize();
            Number queueSize = threadPoolMetrics.getCurrentQueueSize();
            System.out.println("ThreadPoolKey: " + name + ", PoolSize: " + poolSize + ", QueueSize: " + queueSize);
        }
    }


    static HystrixCommand.Setter generateCommandConfig(int coreSize, int maximumSize, int maxQueueSize, int rejThresholdSize) {
        final String commandName = "TestThreadPoolCommand";
        final HystrixCommand.Setter commandConfig = HystrixCommand.Setter
                .withGroupKey(HystrixCommandGroupKey.Factory.asKey(commandName))
                .andCommandKey(HystrixCommandKey.Factory.asKey(commandName))
                .andCommandPropertiesDefaults(
                        HystrixCommandProperties.Setter()
                                .withExecutionTimeoutEnabled(false))
                .andThreadPoolPropertiesDefaults(
                        HystrixThreadPoolProperties.Setter()
                                .withCoreSize(coreSize)
                                .withMaximumSize(maximumSize)
                                .withAllowMaximumSizeToDivergeFromCoreSize(true)
                                .withMaxQueueSize(maxQueueSize)
                                .withQueueSizeRejectionThreshold(rejThresholdSize));
        return commandConfig;
    }

    static void runOnce(HystrixCommand.Setter commandConfig) throws InterruptedException {
        HystrixCommand<Void> command = new HystrixCommand<Void>(commandConfig) {
            @Override
            protected Void run() {
                return null;
            }
        };
        command.execute();
        Thread.sleep(100);
    }
}
