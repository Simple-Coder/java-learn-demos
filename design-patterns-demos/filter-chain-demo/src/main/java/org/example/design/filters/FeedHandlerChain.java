package org.example.design.filters;

/**
 * Created by xiedong
 * Date: 2022/8/14 19:05
 */
public interface FeedHandlerChain<T> {

    void handle(T ctx);

    void next(T ctx);

}
