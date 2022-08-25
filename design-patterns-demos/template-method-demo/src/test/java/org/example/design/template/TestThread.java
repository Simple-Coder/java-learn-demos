package org.example.design.template;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class TestThread {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 10;
        });

        CompletableFuture<Integer> within = CompletableFutureTimeout.completeOnTimeout(1, future, 1, TimeUnit.SECONDS);
        System.out.println(within.get());

        CompletableFuture<String> futureStr = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "正常执行";
        });

        CompletableFuture<String> withinStr = CompletableFutureTimeout.completeOnTimeout("异常执行", futureStr, 1, TimeUnit.SECONDS);
        System.out.println(withinStr.get());
    }
}
