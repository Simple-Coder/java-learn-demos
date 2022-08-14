package org.example.design.filters;

import org.example.design.common.FeedContext;

import java.util.Objects;

/**
 * Created by xiedong
 * Date: 2022/8/14 19:09
 */
public class DefaultFilerChain<T extends FeedContext> implements FeedFilerChain<T> {
    private DefaultFilerChain<T> next;
    private FeedFilter<T> filter;

    public DefaultFilerChain(DefaultFilerChain<T> next, FeedFilter<T> filter) {
        this.next = next;
        this.filter = filter;
    }

    @Override
    public void handle(T ctx) {
        this.filter.doFilter(ctx, this);
    }

    @Override
    public void postHandler(T ctx) {
        FeedFilerChain nextChain = this.next;
        if (Objects.nonNull(nextChain)) {
            nextChain.handle(ctx);
        }
    }
}
