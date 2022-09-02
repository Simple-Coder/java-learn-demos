package org.example.tp.demo;

import java.util.concurrent.Callable;

/**
 * Created by dongxie on 2022/9/2.
 */
public interface TaskWrapper<T> extends Callable<T> {
    /**
     * Task wrapper name, for config
     *
     * @return name
     */
    String name();

    default boolean success() {
        return true;
    }
}
