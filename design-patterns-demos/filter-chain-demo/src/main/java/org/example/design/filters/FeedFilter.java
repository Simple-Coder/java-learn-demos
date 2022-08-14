package org.example.design.filters;

/**
 * Created by xiedong
 * Date: 2022/8/14 17:52
 */
public interface FeedFilter<T> {
    void doFilter(T ctx,FeedFilerChain<T> chain);
}
