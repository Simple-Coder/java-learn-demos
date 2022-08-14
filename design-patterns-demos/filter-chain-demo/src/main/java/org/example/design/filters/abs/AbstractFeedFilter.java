package org.example.design.filters.abs;

import lombok.extern.slf4j.Slf4j;
import org.example.design.common.FeedContext;
import org.example.design.filters.FeedFilerChain;
import org.example.design.filters.FeedFilter;

/**
 * Created by xiedong
 * Date: 2022/8/14 19:13
 */
@Slf4j
public abstract class AbstractFeedFilter<T extends FeedContext> implements FeedFilter<T> {
    public AbstractFeedFilter() {

    }

    @Override
    public void doFilter(T ctx, FeedFilerChain<T> chain) {
        if (ctx.getFilterSelector().matchFilter(this.getClass().getSimpleName())) {
            this.handle(ctx);
        }
        if (ctx.getContinueFlag()) {
            chain.postHandler(ctx);
        }
    }

    protected abstract void handle(T ctx);
}
