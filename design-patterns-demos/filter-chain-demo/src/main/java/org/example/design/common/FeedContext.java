package org.example.design.common;

import org.example.design.enums.BizEnum;
import org.example.design.selector.FilterSelector;
import org.example.design.selector.Selector;

/**
 * Created by xiedong
 * Date: 2022/8/14 19:14
 */
public interface FeedContext {
    BizEnum getBizCode();

    Selector getSelector();

    boolean getContinueFlag();
}
