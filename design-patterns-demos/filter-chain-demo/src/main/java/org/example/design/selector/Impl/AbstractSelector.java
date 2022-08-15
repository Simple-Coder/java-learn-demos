package org.example.design.selector.Impl;

import org.example.design.selector.FeedHandlerSelector;
import org.example.design.selector.FilterSelector;

import java.util.List;

/**
 * Created by xiedong
 * Date: 2022/8/15 21:13
 */
public abstract class AbstractSelector implements FeedHandlerSelector, FilterSelector {
    @Override
    public boolean match(String clz) {
        return false;
    }

    @Override
    public List<String> getNames() {
        return null;
    }
}
