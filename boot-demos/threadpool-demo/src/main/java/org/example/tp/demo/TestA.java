package org.example.tp.demo;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.threadpool.TtlExecutors;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * Created by dongxie on 2022/9/2.
 */
public class TestA {
    @SneakyThrows
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
// 额外的处理，生成修饰了的对象executorService
        executorService = TtlExecutors.getTtlExecutorService(executorService);

        TransmittableThreadLocal<List<String>> context = new TransmittableThreadLocal<>();
        context.set(new ArrayList<>());

        List<Callable<String>> tasks = getCallables(context);

        List<Future<String>> futures = executorService.invokeAll(tasks, 2, TimeUnit.SECONDS);

        List<String> collect = futures.stream().map(f -> {
            try {
                return f.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }).collect(Collectors.toList());
//
//        Runnable task = new Runnable() {
//            @Override
//            public void run() {
//                System.out.println(context.get());
//            }
//        };
//        executorService.submit(task);

        System.out.println("context");
        System.out.println(context.get());
        System.out.println("invokeall返回");
        System.out.println(collect);


        System.out.println("ok---");
        executorService.shutdown();
    }

    private static List<Callable<String>> getCallables(TransmittableThreadLocal<List<String>> context) {
        List<Callable<String>> tasks = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            final int m = i;
            tasks.add(() -> {
                String name = Thread.currentThread().getName();
                context.get().add(name);
                Thread.sleep(m * 1000);
                return name;
            });
        }
        return tasks;
    }
}
