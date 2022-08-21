package org.example.design.template.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.design.template.context.FeedHandlerContext;
import org.example.design.template.extension.ExtensionExecutor;
import org.example.design.template.extension.register.ExtensionBootstrap;
import org.example.design.template.extension.register.ExtensionRegister;
import org.example.design.template.service.FeedFilterI;
import org.example.design.template.service.FeedHandlerI;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by xiedong
 * Date: 2022/8/21 13:04
 */
@Slf4j
public abstract class AbstractFeedHandler<T extends FeedHandlerContext> implements FeedHandlerI<T> {

    @Autowired
    private ExtensionExecutor extensionExecutor;

    @Override
    public void handle(T ctx) {
        //1.根据业务编码找到filter对象
        extensionExecutor.executeVoid(FeedFilterI.class, ctx.getBizScenario(), filter -> filter.preFilter(ctx));
        // 执行preFilter
        //2.组装数据
        this.doHandle(ctx);
        //3.执行postFilter
        extensionExecutor.executeVoid(FeedFilterI.class, ctx.getBizScenario(), filter -> filter.postFilter(ctx));
    }
    protected abstract void doHandle(T ctx);
}
