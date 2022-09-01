package org.example.design.template;

import cn.hutool.core.thread.ThreadUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.util.ArrayList;
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
        List<MyTaskA> tasks = new ArrayList<>();
        tasks.add(new MyTaskA("1", 500));
        tasks.add(new MyTaskA("2", 1000));
        tasks.add(new MyTaskA("3", 2000));
        tasks.add(new MyTaskA("4", 5000));


        List<Future<Object>> futures = pool.invokeAll(tasks, 1, TimeUnit.SECONDS);


        System.out.println(futures);

//        List<Object> collect = pool.invokeAll(tasks, 1, TimeUnit.SECONDS).stream().map(f -> {
//            CommonResult<Object> commonResult = new CommonResult<>();
//            try {
//                System.out.println(f.toString());
//                Object o = f.get();
//                commonResult.setData(o);
//                commonResult.setOk(true);
//            } catch (Exception e) {
//                e.printStackTrace();
//                commonResult.setOk(false);
//            }
//            return commonResult;
//        }).filter(Objects::nonNull).collect(Collectors.toList());
//        System.out.println(collect);
        System.out.println("--------task -----");
    }
}

@Data
@AllArgsConstructor
class MyTaskA implements Callable<Object> {
    private String id;
    private long mis;

    @Override
    public Object call() throws Exception {
        System.out.println(id + "will sleep :" + mis);
        Thread.sleep(mis);
        return id;
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class CommonResult<T> {
    private boolean ok;
    private T data;
}