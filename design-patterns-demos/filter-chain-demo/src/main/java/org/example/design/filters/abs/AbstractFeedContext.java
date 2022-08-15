package org.example.design.filters.abs;

import org.example.design.common.BizAware;
import org.example.design.common.FeedContext;
import org.example.design.enums.BizEnum;
import org.example.design.selector.FilterSelector;
import org.example.design.selector.Selector;

/**
 * Created by xiedong
 * Date: 2022/8/14 19:40
 */
public abstract class AbstractFeedContext implements FeedContext {
    private BizEnum bizEnum;
    private Selector selector;

    public AbstractFeedContext(Selector selector, BizEnum bizEnum) {
        this.selector = selector;
        this.bizEnum = bizEnum;
    }

    @Override
    public BizEnum getBizCode() {
        return this.bizEnum;
    }

    @Override
    public Selector getSelector() {
        return this.selector;
    }

    @Override
    public boolean getContinueFlag() {
        return false;
    }
}
