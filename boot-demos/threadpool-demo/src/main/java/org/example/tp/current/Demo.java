package org.example.tp.current;

import cn.hutool.json.JSONUtil;
import lombok.SneakyThrows;
import org.example.tp.GlobalTp;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Created by xiedong
 * Date: 2022/9/24 18:35
 */
public class Demo {
    @SneakyThrows
    public static void main(String[] args) {
        GlobalTp globalTp = new GlobalTp();
        ThreadPoolExecutor executor = globalTp.getGlobalExecutor();
        int timeOut = 5;
        TimeUnit seconds = TimeUnit.SECONDS;


        List<UserContext> contexts = buildContexts();
        UserHandler userHandler = new UserHandler();


        List<Callable<Void>> tasks = contexts.stream().map(context -> (Callable<Void>) () -> {
            try {
                userHandler.handle(context);
            } catch (Exception e) {
                System.out.println("执行异常了" + e);
            }
            return null;
        }).collect(Collectors.toList());
        executor.invokeAll(tasks, timeOut, seconds);
        System.out.println("result:" + JSONUtil.toJsonStr(contexts));


        globalTp.shutDownGlobalExecutor();
        globalTp.shutDownTaskExecutor();


        System.out.println("ok...");
    }

    private static List<UserContext> buildContexts() {
        List<UserContext> contexts = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            UserContext userContext = new UserContext();
            userContext.setReqName("context" + i);
            contexts.add(userContext);
        }
        return contexts;
    }
}
