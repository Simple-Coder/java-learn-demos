package org.example.metrics.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ForkJoinPool;

/**
 * Created by dongxie on 2022/8/5.
 */
@Configuration
public class RestTemplateConfig {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }


    @Bean
    public ForkJoinPool forkJoinPool() {
        ForkJoinPool forkJoinPool = new ForkJoinPool(20);
        return forkJoinPool;
    }
}
