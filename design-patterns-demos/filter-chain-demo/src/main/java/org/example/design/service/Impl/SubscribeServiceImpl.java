package org.example.design.service.Impl;

import lombok.extern.slf4j.Slf4j;
import org.example.design.enums.BizEnum;
import org.example.design.filters.FeedChainPipeline;
import org.example.design.selector.FilterSelector;
import org.example.design.selector.Impl.LocalFeedSelector;
import org.example.design.selector.Impl.LocalFilterSelector;
import org.example.design.filters.context.SubscribeContext;
import org.example.design.filters.subscribe.ForwardFeedHandler;
import org.example.design.filters.subscribe.LiveFeedHandler;
import org.example.design.filters.subscribe.VideoFeedHandler;
import org.example.design.selector.Selector;
import org.example.design.service.SubscribeService;
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
    private FeedChainPipeline subscribeFilterChainPipeline;


    @Override
    public Object getSubscribeList(Object req) {
        //1.获取selector
        Selector selector = this.getFilterSelector();
        //2.构建上下文
        SubscribeContext subscribeContext = new SubscribeContext(BizEnum.BIZ_XXX, selector);
        //3.请求模型设置到上下文
        subscribeContext.setReq("这是一个subscribe req");
        //4.责任链处理
        subscribeFilterChainPipeline.getFilterChain().handle(subscribeContext);
        //5.返回处理结果
        return subscribeContext.getRsp();
    }

    private Selector getFilterSelector() {
        LocalFeedSelector localFeedSelector = new LocalFeedSelector();
        localFeedSelector.addFeedHandler(LiveFeedHandler.class.getSimpleName());
        localFeedSelector.addFeedHandler(ForwardFeedHandler.class.getSimpleName());
        localFeedSelector.addFeedHandler(VideoFeedHandler.class.getSimpleName());
        return localFeedSelector;
    }
}
