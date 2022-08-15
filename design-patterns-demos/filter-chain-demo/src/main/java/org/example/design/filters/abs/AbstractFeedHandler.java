package org.example.design.filters.abs;

import lombok.extern.slf4j.Slf4j;
import org.example.design.common.FeedContext;
import org.example.design.enums.BizEnum;
import org.example.design.filters.FeedHandlerChain;
import org.example.design.filters.FeedHandler;

/**
 * Created by xiedong
 * Date: 2022/8/14 19:13
 */
@Slf4j
public abstract class AbstractFeedHandler<T extends FeedContext> implements FeedHandler<T> {
    public AbstractFeedHandler() {

    }

    @Override
    public void doFilter(T ctx, FeedHandlerChain<T> chain) {
        //业务处理
        if (ctx.getSelector().match(this.getClass().getSimpleName())) {
            this.handle(ctx);
        }
        //露出规则
        if(ctx.getBizCode().getCode().equals(BizEnum.BIZ_XXX.getCode())){
            this.filterFeed(ctx);
        }

        if (ctx.getContinueFlag()) {
            chain.next(ctx);
        }
    }

    protected abstract void handle(T ctx);

    protected void filterFeed(T ctx){

    }
}
