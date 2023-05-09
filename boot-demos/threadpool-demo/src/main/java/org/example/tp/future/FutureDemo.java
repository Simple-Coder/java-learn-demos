package org.example.tp.future;

import lombok.SneakyThrows;
import org.example.tp.GlobalTp;
import org.springframework.scheduling.annotation.AsyncResult;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by xiedong
 * 2023/5/8 16:34
 */
public class FutureDemo {
    @SneakyThrows
    public static void main(String[] args) {


        long start = System.currentTimeMillis();

        Future<Integer> m1 = m1();
        Future<Integer> m2 = m2();
        Future<Integer> m3 = m3();
        Future<Integer> m4 = m4();

        int add = add(m1.get(), m2().get(), m3.get(), m4().get());

        System.out.println("结果:" + add);
        System.out.println("耗时：" + (System.currentTimeMillis() - start));
    }

    public static int add(int a1, int a2, int a3, int a4) {
        return a1 + a2 + a3 + a4;
    }


    @SneakyThrows
    public static Future<Integer> m1() {
        Thread.sleep(100);
        return new AsyncResult<>(1);
    }

    @SneakyThrows
    public static Future<Integer> m2() {
        Thread.sleep(100);
        return new AsyncResult<>(2);
    }

    @SneakyThrows
    public static Future<Integer> m3() {
        Thread.sleep(100);
        return new AsyncResult<>(3);
    }

    @SneakyThrows
    public static Future<Integer> m4() {
        Thread.sleep(100);
        return new AsyncResult<>(4);
    }
}
