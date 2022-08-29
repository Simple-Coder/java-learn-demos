package org.example.design.template.service.impl.handler;

import lombok.extern.slf4j.Slf4j;
import org.example.design.template.common.BizScenario;
import org.example.design.template.context.FeedHandlerContext;
import org.example.design.template.extension.Extension;
import org.example.design.template.service.impl.AbstractFeedHandler;
import org.springframework.stereotype.Service;

/**
 * Created by xiedong
 * Date: 2022/8/21 13:18
 */
@Service
@Slf4j
@Extension(useCase = BizScenario.HANDLE_CASE,scenario = BizScenario.FEED_TYPE_VIDEO)
public class VideoFeedHandler extends AbstractFeedHandler<FeedHandlerContext> {
    @Override
    protected void doHandle(FeedHandlerContext ctx) {
        log.info("video handle");
        ctx.getRsp().put("video","handle ok");
    }
}