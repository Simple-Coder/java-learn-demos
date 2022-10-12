package org.example.metrics.config;

import com.netflix.config.ConfigurationManager;
import com.netflix.config.DynamicConfiguration;
import com.netflix.config.FixedDelayPollingScheduler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by xiedong
 * Date: 2022/10/12 21:41
 */
@Configuration

public class InitDynamicConfig {

    @Bean
    public DynamicConfiguration dynamicConfiguration(DynamicConfigSource configource) {

        DynamicConfiguration configuration = new DynamicConfiguration(configource,

                new FixedDelayPollingScheduler(30 * 100, 60 * 100, false));

// 启动schedel,定时调用DynamicConfigSource.poll()更新配置

        ConfigurationManager.install(configuration);

        return configuration;

    }

}