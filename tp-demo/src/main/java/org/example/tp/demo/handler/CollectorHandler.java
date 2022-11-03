package org.example.tp.demo.handler;

import lombok.extern.slf4j.Slf4j;
import org.example.tp.demo.dto.ThreadPoolStats;
import org.example.tp.demo.monitor.collector.InternalLogCollector;
import org.example.tp.demo.monitor.collector.LogCollector;
import org.example.tp.demo.monitor.collector.MetricsCollector;
import org.example.tp.demo.monitor.collector.MicroMeterCollector;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ServiceLoader;

/**
 * CollectorHandler related
 *
 * @author yanhom
 * @since 1.0.0
 **/
@Slf4j
public class CollectorHandler {

    private static final Map<String, MetricsCollector> COLLECTORS = new HashMap<>();

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
        MetricsCollector microMeterCollector = new MicroMeterCollector();
        LogCollector logCollector = new LogCollector();
        InternalLogCollector internalLogCollector = new InternalLogCollector();

        microMeterCollector.collect(poolStats);
        logCollector.collect(poolStats);
        internalLogCollector.collect(poolStats);



//        if (poolStats == null || CollectionUtils.isEmpty(types)) {
//            return;
//        }
//        for (String collectorType : types) {
//            MetricsCollector collector = COLLECTORS.get(collectorType.toLowerCase());
//            if (collector != null) {
//                collector.collect(poolStats);
//            }
//        }
    }

    public static CollectorHandler getInstance() {
        return CollectorHandlerHolder.INSTANCE;
    }

    private static class CollectorHandlerHolder {
        private static final CollectorHandler INSTANCE = new CollectorHandler();
    }
}
