package org.example.tp.demo1;

import com.alibaba.ttl.TransmittableThreadLocal;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created by xiedong
 * Date: 2022/9/2 20:28
 */
@AllArgsConstructor
public class Task1 implements TaskResult<Integer> {
    private TransmittableThreadLocal<List<Response<Integer>>> context;
    private long mis;

    @Override
    public Integer call() throws Exception {
        Response<Integer> response = new Response<>();
        response.setSuccess(false);
        if (mis < 0) {
            throw new RuntimeException("mis 不能小于0");
        }
        Thread.sleep(mis);
        response.setData(Integer.parseInt(mis + ""));
        response.setMsg("成功睡眠了" + mis);
        response.setSuccess(true);
        context.get().add(response);
        return Integer.parseInt(mis + "");
    }
}
