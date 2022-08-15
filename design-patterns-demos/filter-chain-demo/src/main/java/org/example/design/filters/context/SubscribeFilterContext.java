package org.example.design.filters.context;

import org.example.design.enums.BizEnum;
import org.example.design.filters.abs.AbstractFeedContext;
import org.example.design.selector.Selector;

/**
 * Created by xiedong
 * Date: 2022/8/15 21:45
 */
public class SubscribeFilterContext extends AbstractFeedContext {
    public SubscribeFilterContext(Selector selector, BizEnum bizEnum) {
        super(selector, bizEnum);
    }
}
