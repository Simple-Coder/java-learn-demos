package org.example.design.filters.abs;

import lombok.Getter;
import lombok.Setter;
import org.example.design.common.BizAware;
import org.example.design.common.FeedContext;
import org.example.design.enums.BizEnum;
import org.example.design.filters.FilterSelector;

/**
 * Created by xiedong
 * Date: 2022/8/14 19:40
 */
public abstract class AbstractFeedContext implements FeedContext {
    private BizEnum bizEnum;
    private FilterSelector filterSelector;

    public AbstractFeedContext(FilterSelector filterSelector, BizEnum bizEnum) {
        this.filterSelector = filterSelector;
        this.bizEnum = bizEnum;
    }

    @Override
    public BizAware getBizCode() {
        return null;
    }

    @Override
    public FilterSelector getFilterSelector() {
        return this.filterSelector;
    }

    @Override
    public boolean getContinueFlag() {
        return false;
    }
}
