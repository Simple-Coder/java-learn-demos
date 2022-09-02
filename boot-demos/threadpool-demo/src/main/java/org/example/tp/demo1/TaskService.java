package org.example.tp.demo1;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.threadpool.TtlExecutors;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * Created by xiedong
 * Date: 2022/9/2 20:28
 */
public class TaskService {
    @SneakyThrows
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
// 额外的处理，生成修饰了的对象executorService
        executorService = TtlExecutors.getTtlExecutorService(executorService);

        TransmittableThreadLocal<List<Response<Integer>>> context = new TransmittableThreadLocal<>();
        context.set(new ArrayList<>());

        List<TaskResult<Integer>> taskResults = new ArrayList<>();
        taskResults.add(new Task1(context,1000));
        taskResults.add(new Task1(context,1200));
        taskResults.add(new Task1(context,3000));
        taskResults.add(new Task1(context,-1));
        List<Future<Integer>> futures = executorService.invokeAll(taskResults, 3, TimeUnit.SECONDS);

        futures.stream().map(f-> {
            try {
                return f.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }).collect(Collectors.toList());

        List<Response<Integer>> responses = context.get();
        System.out.println(responses);
    }
}
