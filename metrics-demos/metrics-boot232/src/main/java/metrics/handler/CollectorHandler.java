package metrics.handler;

import cn.hutool.core.map.MapUtil;
import lombok.extern.slf4j.Slf4j;
import metrics.dto.ThreadPoolStats;
import metrics.monitor.collector.InternalLogCollector;
import metrics.monitor.collector.LogCollector;
import metrics.monitor.collector.MetricsCollector;
import metrics.monitor.collector.MicroMeterCollector;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.ServiceLoader;

/**
 * Created by xiedong
 * Date: 2022/9/21 22:19
 */
@Slf4j
public class CollectorHandler {

    private static final Map<String, MetricsCollector> COLLECTORS = MapUtil.newHashMap();

    private CollectorHandler() {
        ServiceLoader<MetricsCollector> loader = ServiceLoader.load(MetricsCollector.class);
        for (MetricsCollector collector : loader) {
            COLLECTORS.put(collector.type(), collector);
        }

        MetricsCollector microMeterCollector = new MicroMeterCollector();
        LogCollector logCollector = new LogCollector();
        InternalLogCollector internalLogCollector = new InternalLogCollector();
        COLLECTORS.put(microMeterCollector.type(), microMeterCollector);
        COLLECTORS.put(logCollector.type(), logCollector);
        COLLECTORS.put(internalLogCollector.type(), internalLogCollector);
    }

    public void collect(ThreadPoolStats poolStats, List<String> types) {
        if (poolStats == null || CollectionUtils.isEmpty(types)) {
            return;
        }
        for (String collectorType : types) {
            MetricsCollector collector = COLLECTORS.get(collectorType.toLowerCase());
            if (collector != null) {
                collector.collect(poolStats);
            }
        }
    }

    public static CollectorHandler getInstance() {
        return CollectorHandlerHolder.INSTANCE;
    }

    private static class CollectorHandlerHolder {
        private static final CollectorHandler INSTANCE = new CollectorHandler();
    }
}

