package org.example.tp.demo1;

import java.util.concurrent.Callable;

/**
 * Created by xiedong
 * Date: 2022/9/2 20:29
 */
public interface TaskResult<T> extends Callable<Integer> {
}
