package org.example.tp.demo4;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by xiedong
 * Date: 2022/9/21 21:51
 */
public class TestDyTP {

    /**
     * 线程执行器
     **/
    private volatile ThreadPoolExecutor executor;
    /**
     * 核心线程数
     **/
    private Integer corePoolSize = 10;
    /**
     * 最大值线程数
     **/
    private Integer maximumPoolSize = 20;
    /**
     * 待执行任务的队列的长度
     **/
    private Integer workQueueSize = 1000;
    /**
     * 线程空闲时间
     **/
    private Long keepAliveTime = 1000L;
    /**
     * 线程名
     **/
    private String threadName;

    public TestDyTP() {
        init();
    }

    /**
     * 初始化
     */
    private void init() {
        System.out.println("线程池初始化中..........");
        if (executor == null) {
            synchronized (TestDyTP.class) {
                if (executor == null) {
                    String corePoolSizeProperty = "10";
                    System.out.println("修改前的核心线程池:" + corePoolSizeProperty);
                    String maximumPoolSizeProperty = "10";
                    String keepAliveTImeProperty = "6000";
                    BlockingQueue<Runnable> workQueueProperty = new LinkedBlockingQueue<>(workQueueSize);
                    executor = new ThreadPoolExecutor(Integer.parseInt(corePoolSizeProperty), Integer.valueOf(maximumPoolSizeProperty), Long.valueOf(keepAliveTImeProperty), TimeUnit.MILLISECONDS, workQueueProperty);
                }
            }
        }
    }

    public static void main(String[] args) {
        TestDyTP testDyTP = new TestDyTP();
        ThreadPoolExecutor executor = testDyTP.getExecutor();
        executor.execute(() -> {
            System.out.println(Thread.currentThread().getName());
        });

        testDyTP.refreshThreadPool(ParamsEnum.CORE_POOL_SIZE.getParam(), "20");
        executor.execute(() -> {
            System.out.println(Thread.currentThread().getName());
        });
        System.out.println();
    }


    /**
     * 更新线程池配置      * @param key      * @param newValue
     */
    private void refreshThreadPool(String key, String newValue) {
        if (executor == null) {
            return;
        }
        if (ParamsEnum.CORE_POOL_SIZE.getParam().equals(key)) {
            executor.setCorePoolSize(Integer.valueOf(newValue));
            System.out.println(StrUtil.format("修改核心线程数key={},value={}", key, newValue));
        }
        if (ParamsEnum.MAXIMUM_POOL_SIZE.getParam().equals(key)) {
            executor.setMaximumPoolSize(Integer.valueOf(newValue));
            System.out.println(StrUtil.format("修改最大线程数key={},value={}", key, newValue));
        }
        if (ParamsEnum.KEEP_ALIVE_TIME.getParam().equals(key)) {
            executor.setKeepAliveTime(Integer.valueOf(newValue), TimeUnit.MILLISECONDS);
            System.out.println(StrUtil.format("修改线程空闲时间key={},value={}", key, newValue));
        }
    }

    public ThreadPoolExecutor getExecutor() {
        return executor;
    }
}
