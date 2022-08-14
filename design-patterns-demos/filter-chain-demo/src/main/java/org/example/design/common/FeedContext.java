package org.example.design.common;

import org.example.design.filters.FilterSelector;

/**
 * Created by xiedong
 * Date: 2022/8/14 19:14
 */
public interface FeedContext {
    BizAware getBizCode();

    FilterSelector getFilterSelector();

    boolean getContinueFlag();
}
