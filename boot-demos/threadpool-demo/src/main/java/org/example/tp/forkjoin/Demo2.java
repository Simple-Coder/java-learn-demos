package org.example.tp.forkjoin;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

/**
 * Created by xiedong
 * 2023/5/9 10:30
 */
public class Demo2 {
    @SneakyThrows
    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool(10);

        List<Integer> list = Lists.newArrayList();

        for(int i =0;i<10;i++){
            list.add(i);
        }

        long beginTime = System.currentTimeMillis();
        List<String>  strList = Lists.newArrayList();
//        List<String>  strList = Collections.synchronizedList(new ArrayList<>());
        forkJoinPool.submit(() -> {
            list.stream().parallel().forEach(item -> {
                System.out.println("threadName:" + Thread.currentThread().getName());
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                strList.add("item" + item);
            });
        }).get();

        System.out.println("size:" + strList.size() + " detail:" + JSON.toJSONString(strList) + " costTime:" + (System.currentTimeMillis() - beginTime));

    }
}
