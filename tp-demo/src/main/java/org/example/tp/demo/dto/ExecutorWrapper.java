package org.example.tp.demo.dto;

import lombok.Data;

import java.util.List;
import java.util.concurrent.Executor;

/**
 * Executor wrapper
 *
 * @author yanhom
 * @since 1.0.3
 **/
@Data
public class ExecutorWrapper {

    private String threadPoolName;

    private Executor executor;

    private String threadPoolAliasName;


    public ExecutorWrapper(String threadPoolName, Executor executor) {
        this.threadPoolName = threadPoolName;
        this.executor = executor;
    }
}
