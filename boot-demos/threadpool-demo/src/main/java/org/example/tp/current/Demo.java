package org.example.tp.current;

import cn.hutool.json.JSONUtil;
import lombok.SneakyThrows;
import org.example.tp.GlobalTp;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;

/**
 * Created by xiedong
 * Date: 2022/9/24 18:35
 */
public class Demo {
    @SneakyThrows
    public static void main(String[] args) {
        GlobalTp globalTp = new GlobalTp();
        ThreadPoolExecutor executor = globalTp.getExecutor();


        List<UserContext> contexts = buildContexts();
        UserHandler userHandler = new UserHandler();


        List<Callable<RspBean>> tasks = contexts.stream().map(f -> (Callable<RspBean>) () -> {
            userHandler.handle(f);
            return f.getRspBean();
        }).collect(Collectors.toList());
        executor.invokeAll(tasks);
        System.out.println("result:" + JSONUtil.toJsonStr(contexts));
        executor.shutdown();
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
