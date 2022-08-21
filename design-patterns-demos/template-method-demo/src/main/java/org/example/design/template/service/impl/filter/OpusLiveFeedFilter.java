package org.example.design.template.service.impl.filter;

import lombok.extern.slf4j.Slf4j;
import org.example.design.template.common.BizScenario;
import org.example.design.template.context.FeedHandlerContext;
import org.example.design.template.extension.Extension;
import org.example.design.template.service.impl.AbstractFeedFilter;
import org.springframework.stereotype.Service;

/**
 * Created by xiedong
 * Date: 2022/8/21 13:22
 */
@Service
@Slf4j
//作品tab的直播  filter
@Extension(bizId = BizScenario.OPUS_BIZ_ID,useCase = BizScenario.FILTER_CASE)
public class OpusLiveFeedFilter extends AbstractFeedFilter<FeedHandlerContext> {
    @Override
    public void preFilter(FeedHandlerContext ctx) {
        super.preFilter(ctx);
        log.info("作品tab prefilter");
    }

    @Override
    public void postFilter(FeedHandlerContext ctx) {
        log.info("作品tab postfilter");
    }
}
