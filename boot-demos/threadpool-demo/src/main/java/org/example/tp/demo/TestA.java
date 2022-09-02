package org.example.tp.demo;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.threadpool.TtlExecutors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by dongxie on 2022/9/2.
 */
public class TestA {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
// 额外的处理，生成修饰了的对象executorService
        executorService = TtlExecutors.getTtlExecutorService(executorService);

        TransmittableThreadLocal<String> context = new TransmittableThreadLocal<String>();
        context.set("value-set-in-parent");
        Runnable task = new Runnable() {
            @Override
            public void run() {
                System.out.println(context.get());
                context.set("child");
            }
        };
        executorService.submit(task);

        System.out.println(context.get());




        System.out.println("ok---");
        executorService.shutdown();
    }
}
