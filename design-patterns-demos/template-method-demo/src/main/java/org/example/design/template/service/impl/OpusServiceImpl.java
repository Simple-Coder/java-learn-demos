package org.example.design.template.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.design.template.common.BizScenario;
import org.example.design.template.context.FeedHandlerContext;
import org.example.design.template.extension.ExtensionExecutor;
import org.example.design.template.service.FeedFilterI;
import org.example.design.template.service.FeedHandlerI;
import org.example.design.template.service.OpusServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by xiedong
 * Date: 2022/8/21 16:16
 */
@Service
@Slf4j
public class OpusServiceImpl implements OpusServiceI {
    @Autowired
    private ExtensionExecutor extensionExecutor;

    @Override
    public Object getOpusList(Object req) {
        //1.构建context
        BizScenario bizScenario = BizScenario.valueOf(BizScenario.OPUS_BIZ_ID,
                BizScenario.FILTER_CASE);
        FeedHandlerContext handlerContext = new FeedHandlerContext();
        handlerContext.setBizScenario(bizScenario);
        handlerContext.setReq("这是作品tab请求");
        //2.执行处理
        extensionExecutor.executeVoid(FeedHandlerI.class
                , BizScenario.valueOf(BizScenario.DEFAULT_BIZ_ID, BizScenario.HANDLE_CASE, BizScenario.FEED_TYPE_VIDEO)
                , hander -> hander.handle(handlerContext));
        //获取返回结果
        return handlerContext.getRsp();
    }
}
