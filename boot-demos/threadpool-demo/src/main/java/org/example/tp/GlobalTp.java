package org.example.tp;

import sun.nio.ch.ThreadPool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by xiedong
 * Date: 2022/9/24 18:40
 */
public class GlobalTp {

    private ThreadPoolExecutor globalExetor;
    private ThreadPoolExecutor taskExecutor;

    public ThreadPoolExecutor getGlobalExecutor() {
        if (globalExetor == null) {
            synchronized (GlobalTp.class) {
                if (globalExetor == null) {
                    globalExetor = new ThreadPoolExecutor(10, 10,
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
        }
        return globalExetor;
    }

    public void shutDownGlobalExecutor() {
        if (this.globalExetor != null) {
            this.globalExetor.shutdown();
        }
    }


    public ThreadPoolExecutor getTaskExecutor() {
        if (taskExecutor == null) {
            synchronized (GlobalTp.class) {
                if (taskExecutor == null) {
                    taskExecutor = new ThreadPoolExecutor(10, 10,
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
        }
        return taskExecutor;
    }

    public void shutDownTaskExecutor() {
        if (this.taskExecutor != null) {
            this.taskExecutor.shutdown();
        }
    }
}
