package org.example.tp.demo;

import org.example.tp.demo.core.DtpRegistry;
import org.example.tp.demo.monitor.DtpMonitor;
import org.example.tp.demo.spring.ApplicationContextHolder;
import org.example.tp.demo.spring.DtpPostProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

/**
 * Created by xiedong
 * Date: 2022/11/3 21:31
 */
@Configuration
public class BaseBeanAutoConfiguration {
    @Bean
    public ApplicationContextHolder dtpApplicationContextHolder() {
        return new ApplicationContextHolder();
    }
    @Bean
    @ConditionalOnMissingBean
    public DtpRegistry dtpRegistry() {
        return new DtpRegistry();
    }

    @Bean
    @DependsOn({"dtpApplicationContextHolder"})
    @ConditionalOnMissingBean
    public DtpPostProcessor dtpPostProcessor() {
        return new DtpPostProcessor();
    }

    @Bean
    @ConditionalOnMissingBean
    public DtpMonitor dtpMonitor() {
        return new DtpMonitor(null);
    }

}
