package org.example.metrics.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.TimeInterval;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.example.metrics.outer.OuterClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Created by dongxie on 2022/9/2.
 */
@RestController
@Slf4j
@RequestMapping("/tp")
public class TPController {
    @Autowired
    private ForkJoinPool forkJoinPool;
    @Autowired
    private OuterClient outerClient;

    //http://10.2.9.174:9999/tp/test
    @GetMapping("/test")
    @SneakyThrows
    public Object get() {

        List<MyTask> myTaskList = new ArrayList<>();
        myTaskList.add(new MyTask(1, this.outerClient));
        myTaskList.add(new MyTask(2, this.outerClient));
        myTaskList.add(new MyTask(3, this.outerClient));
        myTaskList.add(new MyTask(4, this.outerClient));

        TimeInterval timer = DateUtil.timer();
        List<Response> collect = forkJoinPool.invokeAll(myTaskList, 3, TimeUnit.SECONDS).stream().map(f -> {
            try {
                return f.get();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }).filter(Objects::nonNull).collect(Collectors.toList());
        System.out.println("整体耗时"+timer.intervalPretty());
        return collect;
    }
}

@Data
class Response {
    JSONObject resp;
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class MyTask implements Callable<Response> {
    private int type;
    private OuterClient outerClient;

    @Override
    public Response call() throws Exception {
        TimeInterval timer = DateUtil.timer();


        Response response = new Response();
        JSONObject jsonObject = new JSONObject();
        response.setResp(jsonObject);
        jsonObject.put("type", type);
        Object hystrixTest = outerClient.getHystrixTest(1);
        jsonObject.put("hystrix", hystrixTest);
        if (type % 2 == 0) {
            String a = outerClient.getA();
            jsonObject.put("getA", a);
        }
        String b = outerClient.getB();
        jsonObject.put("getB", b);


//        Object hystrixTest1 = outerClient.getHystrixTest(10);
//        jsonObject.put("hystrix111", hystrixTest1);
        System.out.println(type+"耗时："+timer.intervalPretty());
        return response;
    }
}