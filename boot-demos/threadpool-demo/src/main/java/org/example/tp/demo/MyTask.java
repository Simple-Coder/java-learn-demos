package org.example.tp.demo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Objects;
import java.util.concurrent.Callable;

/**
 * Created by dongxie on 2022/9/2.
 */
@Data
@AllArgsConstructor
public class MyTask implements TaskWrapper<Integer> {
    private String name;
    private long mis;

    @Override
    public Integer call() throws Exception {
        System.out.println(name + "will sleep :" + mis);
        if (this.mis < 0) {
            throw new RuntimeException("睡眠时间不能小于0");
        }
        Thread.sleep(mis);
        return Integer.parseInt(Objects.toString(this.mis));
    }

    @Override
    public String name() {
        return this.name;
    }
}
