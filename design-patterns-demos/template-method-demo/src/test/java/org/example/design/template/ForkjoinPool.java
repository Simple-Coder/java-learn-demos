package org.example.design.template;

import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * Created by xiedong
 * Date: 2022/8/27 21:51
 */
public class ForkjoinPool {
    @SneakyThrows
    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        List<MyTask> tasks = new ArrayList<>();
        tasks.add(new MyTask("1", 500));
        tasks.add(new MyTask("2", 1000));
        tasks.add(new MyTask("3", 2000));
        tasks.add(new MyTask("4", 5000));

        List<Object> collect = pool.invokeAll(tasks, 1, TimeUnit.SECONDS).stream().map(f -> {
            try {
                return f.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }).filter(Objects::nonNull).collect(Collectors.toList());
        System.out.println(collect);
    }
}

@Data
@AllArgsConstructor
class MyTask implements Callable<Object> {
    private String id;
    private long mis;

    @Override
    public Object call() throws Exception {
        System.out.println(id + "will sleep :" + mis);
        Thread.sleep(mis);
        return id;
    }
}