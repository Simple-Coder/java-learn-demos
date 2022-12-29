package org.example.tp.demo.monitor;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.binder.jvm.ExecutorServiceMetrics;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.tp.demo.convert.MetricsConverter;
import org.example.tp.demo.core.DtpRegistry;
import org.example.tp.demo.dto.ExecutorWrapper;
import org.example.tp.demo.dto.ThreadPoolStats;
import org.example.tp.demo.handler.CollectorHandler;
import org.example.tp.demo.spring.ApplicationContextHolder;
import org.example.tp.demo.thread.NamedThreadFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.Ordered;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * DtpMonitor related
 *
 * @author yanhom
 * @since 1.0.0
 **/
@Slf4j
public class DtpMonitor implements ApplicationRunner, Ordered {

    private MeterRegistry meterRegistry;

    public DtpMonitor(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    private static final ScheduledExecutorService MONITOR_EXECUTOR = new ScheduledThreadPoolExecutor(
            1, new NamedThreadFactory("dtp-monitor", true));


    @Override
    public void run(ApplicationArguments args) {
        MONITOR_EXECUTOR.scheduleWithFixedDelay(this::run,
                0, 1, TimeUnit.SECONDS);
    }

    private void run() {

        List<String> commonNames = DtpRegistry.listAllCommonNames();
//        checkAlarm(dtpNames);
//        collect(dtpNames, commonNames);
//        collect(commonNames);
    }

    private void collect(List<String> commonNames) {
        commonNames.forEach(x -> {

            ExecutorServiceMetrics.monitor(meterRegistry, MONITOR_EXECUTOR, x, Collections.emptyList());


            ExecutorWrapper wrapper = DtpRegistry.getCommonExecutor(x);
            ThreadPoolStats poolStats = MetricsConverter.convert(wrapper);
            doCollect(poolStats);
        });
    }


    private void doCollect(ThreadPoolStats threadPoolStats) {
        try {
            CollectorHandler.getInstance().collect(threadPoolStats, Arrays.asList());
        } catch (Exception e) {
            log.error("DynamicTp monitor, metrics collect error.", e);
        }
    }


    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE + 2;
    }
}