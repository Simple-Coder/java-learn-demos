package org.example.design.template;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.TimeInterval;
import cn.hutool.core.util.RandomUtil;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class TestThread {
    @SneakyThrows
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        List<CompletableFuture<Integer>> futures = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
                try {
                    Thread.sleep(RandomUtil.randomLong(2000, 3000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return 10;
            }, forkJoinPool);
            futures.add(future);
        }


        TimeInterval timer = DateUtil.timer();
        List<Integer> collect = futures.stream().map(future -> {
            try {
                return CompletableFutureTimeout.completeOnTimeout(-1, future, 1, TimeUnit.SECONDS).get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            return -2;
        }).collect(Collectors.toList());
        String s = timer.intervalPretty();
        System.out.println(s);
        System.out.println(collect);


//        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
//            try {
//                Thread.sleep(2000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            return 10;
//        });
//
//
//        CompletableFuture<Integer> within = CompletableFutureTimeout.completeOnTimeout(1, future, 1, TimeUnit.SECONDS);
//        System.out.println(within.get());
//
//        CompletableFuture<String> futureStr = CompletableFuture.supplyAsync(() -> {
//            try {
//                Thread.sleep(2000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            return "正常执行";
//        });
//
//        CompletableFuture<String> withinStr = CompletableFutureTimeout.completeOnTimeout("异常执行", futureStr, 1, TimeUnit.SECONDS);
//        System.out.println(withinStr.get());
    }
}
