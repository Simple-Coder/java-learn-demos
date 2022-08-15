package org.example.design.filters;

/**
 * Created by xiedong
 * Date: 2022/8/14 19:47
 */
public class FeedChainPipeline<T extends FeedHandler> {
    private DefaultFilerChain last;

    public FeedChainPipeline() {
    }

    public DefaultFilerChain getFilterChain() {
        return this.last;
    }

    public FeedChainPipeline<T> addFilter(T filter) {
        DefaultFilerChain newChain = new DefaultFilerChain(this.last, filter);
        this.last = newChain;
        return this;
    }

    public FeedChainPipeline<T> addFilter(String desc, T filter) {
        DefaultFilerChain newChain = new DefaultFilerChain(this.last, filter);
        this.last = newChain;
        return this;
    }

}
