package org.example.design.filters;

/**
 * Created by xiedong
 * Date: 2022/8/14 19:05
 */
public interface FeedFilerChain<T> {

    void handle(T ctx);

    void postHandler(T ctx);

}
