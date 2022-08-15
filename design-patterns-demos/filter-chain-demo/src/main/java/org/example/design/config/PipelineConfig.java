package org.example.design.config;

import org.example.design.filters.FeedChainPipeline;
import org.example.design.filters.subscribe.ForwardFeedHandler;
import org.example.design.filters.subscribe.LiveFeedHandler;
import org.example.design.filters.subscribe.VideoFeedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by xiedong
 * Date: 2022/8/14 19:45
 */
@Configuration
public class PipelineConfig {
    @Autowired
    private LiveFeedHandler liveFeedFilter;
    @Autowired
    private ForwardFeedHandler forwardFeedFilter;
    @Autowired
    private VideoFeedHandler videoFeedHandler;

    @Bean("subscribeFilterChainPipeline")
    public FeedChainPipeline subscribePipeline() {
        FeedChainPipeline filterChainPipeline = new FeedChainPipeline();
        filterChainPipeline.addFilter("直播类型", liveFeedFilter);
        filterChainPipeline.addFilter("转发类型", forwardFeedFilter);
        filterChainPipeline.addFilter("视频类型", videoFeedHandler);
        return filterChainPipeline;
    }

}
