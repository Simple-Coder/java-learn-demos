package org.example.design.filters;

import org.example.design.common.FeedContext;

/**
 * Created by xiedong
 * Date: 2022/8/14 19:47
 */
public class FilterChainPipeline<T extends FeedFilter> {
    private DefaultFilerChain last;

    public FilterChainPipeline() {
    }

    public DefaultFilerChain getFilterChain() {
        return this.last;
    }

    public FilterChainPipeline<T> addFilter(T filter) {
        DefaultFilerChain newChain = new DefaultFilerChain(this.last, filter);
        this.last = newChain;
        return this;
    }

    public FilterChainPipeline<T> addFilter(String desc, T filter) {
        DefaultFilerChain newChain = new DefaultFilerChain(this.last, filter);
        this.last = newChain;
        return this;
    }

}
