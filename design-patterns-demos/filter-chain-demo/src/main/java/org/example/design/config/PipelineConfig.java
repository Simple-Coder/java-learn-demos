package org.example.design.config;

import org.example.design.filters.FilterChainPipeline;
import org.example.design.filters.subscribe.ForwardFeedFilter;
import org.example.design.filters.subscribe.LiveFeedFilter;
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
    private LiveFeedFilter liveFeedFilter;
    @Autowired
    private ForwardFeedFilter forwardFeedFilter;

    @Bean("subscribeFilterChainPipeline")
    public FilterChainPipeline subscribePipeline() {
        FilterChainPipeline filterChainPipeline = new FilterChainPipeline();
        filterChainPipeline.addFilter("直播类型", liveFeedFilter);
        filterChainPipeline.addFilter("转发类型", forwardFeedFilter);
        return filterChainPipeline;
    }

}
