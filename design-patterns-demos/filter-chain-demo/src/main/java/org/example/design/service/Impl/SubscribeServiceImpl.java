package org.example.design.service.Impl;

import lombok.extern.slf4j.Slf4j;
import org.example.design.enums.BizEnum;
import org.example.design.filters.FilterChainPipeline;
import org.example.design.filters.FilterSelector;
import org.example.design.filters.LocalFilterSelector;
import org.example.design.filters.context.SubscribeContext;
import org.example.design.filters.subscribe.ForwardFeedFilter;
import org.example.design.filters.subscribe.LiveFeedFilter;
import org.example.design.service.SubscribeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by xiedong
 * Date: 2022/8/14 20:06
 */
@Service
@Slf4j
public class SubscribeServiceImpl implements SubscribeService {

    @Resource
    private FilterChainPipeline subscribeFilterChainPipeline;


    @Override
    public Object getSubscribeList(Object req) {
        //1.获取selector
        FilterSelector selector = this.getFilterSelector();
        //2.构建上下文
        SubscribeContext subscribeContext = new SubscribeContext(BizEnum.BIZ_XXX, selector);
        //3.请求模型设置到上下文
        subscribeContext.setReq("这是一个subscribe req");
        //4.责任链处理
        subscribeFilterChainPipeline.getFilterChain().handle(subscribeContext);
        //5.返回处理结果
        return subscribeContext.getRsp();
    }

    private FilterSelector getFilterSelector() {
        LocalFilterSelector localFilterSelector = new LocalFilterSelector();
        localFilterSelector.addFilter(LiveFeedFilter.class.getSimpleName());
        localFilterSelector.addFilter(ForwardFeedFilter.class.getSimpleName());
        return localFilterSelector;
    }
}
