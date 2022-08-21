package org.example.design.template.service.impl.filter;

import lombok.extern.slf4j.Slf4j;
import org.example.design.template.context.FeedHandlerContext;
import org.example.design.template.service.impl.AbstractFeedFilter;
import org.springframework.stereotype.Service;

/**
 * Created by xiedong
 * Date: 2022/8/21 13:21
 */
@Slf4j
@Service
public class HomeForwardFeedFilter extends AbstractFeedFilter<FeedHandlerContext> {
    @Override
    public void preFilter(FeedHandlerContext ctx) {
        super.preFilter(ctx);
    }

    @Override
    public void postFilter(FeedHandlerContext ctx) {
        super.postFilter(ctx);
    }
}
