package org.example.design.template;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.TimeInterval;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.SneakyThrows;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class TestB {
    @SneakyThrows
    public static void main(String[] args) {
//        List<Integer> a = new ArrayList<>();
//        a.add(1);
//        a.add(6);
//        a.add(4);
//        a.add(3);
//        a.add(2);
//        //修改原数组
//        List<Integer> sort = CollUtil.sort(a, (n, m) -> m.compareTo(n));
//        List<Integer> sor1t = CollUtil.sort(a, (m, n) -> m.compareTo(n));
//        System.out.println();
//        List<Integer> collect = a.stream().filter(a1 -> a1 > 4).collect(Collectors.toList());
//        System.out.println();


//        ForkJoinPool forkJoinPool = new ForkJoinPool();
//        Collection<Callable<Object>> runnables = new ArrayList<>();
//        runnables.add(new MyTask("1", RandomUtil.randomInt(5000)));
//        runnables.add(new MyTask("2", RandomUtil.randomInt(5000)));
//        runnables.add(new MyTask("3", RandomUtil.randomInt(5000)));
//        runnables.add(new MyTask("4", RandomUtil.randomInt(5000)));
//        runnables.add(new MyTask("5", RandomUtil.randomInt(5000)));
//        List<Future<Object>> futures = forkJoinPool.invokeAll(runnables,1,TimeUnit.MILLISECONDS);
//        List<Object> collect1 = futures.stream().map(f -> {
//            try {
//                return f.get();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } catch (ExecutionException e) {
////                f.cancel(true);
//                e.printStackTrace();
//            } catch (Exception e) {
////                f.cancel(true);
//                e.printStackTrace();
//            }
//            return null;
//        }).collect(Collectors.toList());
//        System.out.println(collect1);

        TimeInterval timer1 = DateUtil.timer();


        ForkJoinPool forkJoinPool = new ForkJoinPool();
        Collection<Callable<Object>> runnables = new ArrayList<>();
        runnables.add(new MyTaskA("1", 500));
        runnables.add(new MyTaskA("2", 1000));
        runnables.add(new MyTaskA("3", 1000));
        runnables.add(new MyTaskA("4", 2000));
        runnables.add(new MyTaskA("5", 5000));
        TimeInterval timer = DateUtil.timer();
        List<Future<Object>> futures = forkJoinPool.invokeAll(runnables, 1, TimeUnit.SECONDS);
        List<Object> collect1 = futures.stream().map(f -> {
            try {
                return f.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
//                f.cancel(true);
                e.printStackTrace();
            } catch (Exception e) {
//                f.cancel(true);
                e.printStackTrace();
            }
            return null;
        }).collect(Collectors.toList());
        System.out.println(timer.intervalRestart());
        Thread.sleep(1000);
        System.out.println(timer.intervalRestart());
        System.out.println(collect1);
    }
}

@Data
@AllArgsConstructor
class MyTask implements Callable<Object> {
    private String id;
    private long mis;

    @Override
    public Object call() throws Exception {
        System.out.println(id + "will sleep" + mis);
        Thread.sleep(mis);
        return id;
    }
}
