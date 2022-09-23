package org.example.tp.demo.future;

import java.util.concurrent.*;

/**
 * Created by dongxie on 2022/9/2.
 */
public class TestA {
    public static void main(String[] args)  {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 10,
                60, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(300),
                Executors.defaultThreadFactory(),
                //拒绝策略：由调用线程处理
//                new ThreadPoolExecutor.CallerRunsPolicy()
                //拒绝策略：丢弃任务抛出reject异常
                new ThreadPoolExecutor.AbortPolicy()
        );





//        try {
//            CompletableFuture<Callable<Object>> callableCompletableFuture = CompletableFuture.supplyAsync(() -> {
//                return new Callable<>() {
//                }
//            }, threadPoolExecutor);
//            callableCompletableFuture.
//
//
//            CompletableFuture.allOf(callableCompletableFuture).get();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
    }
}
