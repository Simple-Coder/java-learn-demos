package org.example.tp.demo;

import lombok.SneakyThrows;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * Created by dongxie on 2022/9/2.
 */
public class TestB {
    @SneakyThrows
    public static void main(String[] args) {
        int timeout = 1;

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 10,
                60, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(300),
                Executors.defaultThreadFactory(),
                //拒绝策略：由调用线程处理
//                new ThreadPoolExecutor.CallerRunsPolicy()
                //拒绝策略：丢弃任务抛出reject异常
                new ThreadPoolExecutor.AbortPolicy()
        );

        List<MyTask> taskWrappers = new ArrayList<>();
        taskWrappers.add(new MyTask("feed1", 100));
        taskWrappers.add(new MyTask("feed2", 2000));
        taskWrappers.add(new MyTask("feed3", 3000));
        taskWrappers.add(new MyTask("feed4", 4000));
        taskWrappers.add(new MyTask("feed5", -1));

        Iterator<MyTask> iterator = taskWrappers.iterator();
        List<Future<Integer>> futures = threadPoolExecutor.invokeAll(taskWrappers, timeout, TimeUnit.SECONDS);
        List<Response> responses = futures.stream().map(f -> {
            MyTask next = iterator.next();
            try {
                Integer integer = f.get();
                return Response.ok(integer);
            } catch (InterruptedException | ExecutionException e) {
                return Response.failed(next.getName() + ":中断异常" + e.getMessage());
            } catch (Exception e) {
                return Response.failed(next.getName() + ":执行异常" + e.getMessage());
            }
        }).collect(Collectors.toList());
        System.out.println(responses);
        System.out.println("ok----");

    }
}
