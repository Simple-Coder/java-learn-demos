package org.example.tp.forkjoin;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

/**
 * Created by xiedong
 * 2023/5/6 16:21
 */
@Slf4j
public class ForkJoinDemo {
    @SneakyThrows
    public static void main(String[] args) {
        final List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        List<Integer> result = Collections.synchronizedList(new ArrayList<>());
        long start = System.currentTimeMillis();
        list.parallelStream().forEach(numbser -> {
            Thread c = Thread.currentThread();
            result.add(numbser);
            System.out.println(c.getName());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println("common耗时：" + (System.currentTimeMillis() - start));
        System.out.println("result：" + JSON.toJSONString(result));

        result.clear();
        ForkJoinPool forkJoinPool = new ForkJoinPool(50);
        start = System.currentTimeMillis();
        forkJoinPool.submit(() -> {
            list.parallelStream().forEach(numbser -> {
                Thread c = Thread.currentThread();
                result.add(numbser);
                System.out.println(c.getName());
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }).get();
        System.out.println("自定义耗时：" + (System.currentTimeMillis() - start));
        System.out.println("result：" + JSON.toJSONString(result));


//        for (int i = 1; i <= 1; i++) {
//            new Thread("test-" + i) {
//                String currentThreaName = this.getName();
//
//                @Override
//                public void run() {
//                    list.parallelStream()
//                    list.stream().parallel()
//                            .forEach(numbser -> {
//                                Thread c = Thread.currentThread();
//
//                                log.info("{} --->{}:{}:{}", currentThreaName, c.getClass().getName(), c.getName(), c.getId());
////                                System.out.println(currentThreaName + "===> "
////                                        + c.getClass().getName() + ":" + c.getName() + ":" + c.getId());
//                                try {
//                                    Thread.sleep(10000);
//                                } catch (InterruptedException e) {
//                                    e.printStackTrace();
//                                }
//                            });
//                }
//            }.start();
//        }
        Thread.sleep(Integer.MAX_VALUE);
    }
}
