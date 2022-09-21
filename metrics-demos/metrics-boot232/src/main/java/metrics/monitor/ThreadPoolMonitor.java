package metrics.monitor;

import cn.hutool.core.thread.NamedThreadFactory;
import lombok.extern.slf4j.Slf4j;
import metrics.converter.MetricsConverter;
import metrics.dto.ThreadPoolStats;
import metrics.em.CollectorTypeEnum;
import metrics.handler.CollectorHandler;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by xiedong
 * Date: 2022/9/21 22:11
 */
@Slf4j
@Component
public class ThreadPoolMonitor implements ApplicationRunner, Ordered {
    @Autowired
    private ApplicationContext applicationContext;
    private static final ScheduledExecutorService MONITOR_EXECUTOR = new ScheduledThreadPoolExecutor(
            1, new NamedThreadFactory("boot-tp-monitor", true));

    @Override
    public void run(ApplicationArguments args) {
        MONITOR_EXECUTOR.scheduleWithFixedDelay(this::run,
                0, 1, TimeUnit.SECONDS);
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE + 2;
    }

    private void run() {
        ThreadPoolExecutor globalTPExecutor = applicationContext.getBean("globalTPExecutor", ThreadPoolExecutor.class);
        collect(globalTPExecutor);
    }

    private void collect(ThreadPoolExecutor globalTPExecutor) {
        ThreadPoolStats convert = MetricsConverter.convertCommon(globalTPExecutor);
        doCollect(convert);
    }

    private void doCollect(ThreadPoolStats threadPoolStats) {
        try {
            CollectorHandler.getInstance().collect(threadPoolStats, Lists.newArrayList(CollectorTypeEnum.MICROMETER.name()));
        } catch (Exception e) {
            log.error("DynamicTp monitor, metrics collect error.", e);
        }
    }
}
