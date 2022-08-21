package org.example.design.template.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.design.template.context.FeedHandlerContext;
import org.example.design.template.service.FeedFilterI;

/**
 * Created by xiedong
 * Date: 2022/8/21 13:14
 */
@Slf4j
public abstract class AbstractFeedFilter<T extends FeedHandlerContext> implements FeedFilterI<T> {
    @Override
    public void preFilter(T ctx) {

    }

    @Override
    public void postFilter(T ctx) {

    }
}
