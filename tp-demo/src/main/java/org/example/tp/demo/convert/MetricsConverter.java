package org.example.tp.demo.convert;

import org.example.tp.demo.dto.ExecutorWrapper;
import org.example.tp.demo.dto.ThreadPoolStats;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * MetricsConverter related
 *
 * @author yanhom
 * @since 1.0.0
 **/
public class MetricsConverter {

    private MetricsConverter() {
    }
//
//    public static ThreadPoolStats convert(DtpExecutor executor) {
//        if (executor == null) {
//            return null;
//        }
//        ThreadPoolStats poolStats = convertCommon(executor);
//        poolStats.setPoolName(executor.getThreadPoolName());
//        poolStats.setRejectHandlerName(executor.getRejectHandlerName());
//        poolStats.setRejectCount(executor.getRejectCount());
//        poolStats.setRunTimeoutCount(executor.getRunTimeoutCount());
//        poolStats.setQueueTimeoutCount(executor.getQueueTimeoutCount());
//        poolStats.setDynamic(true);
//        return poolStats;
//    }

    public static ThreadPoolStats convert(ExecutorWrapper wrapper) {
        if (wrapper.getExecutor() == null) {
            return null;
        }
        ThreadPoolStats poolStats = convertCommon((ThreadPoolExecutor) wrapper.getExecutor());
        poolStats.setPoolName(wrapper.getThreadPoolName());
        poolStats.setDynamic(false);
        return poolStats;
    }

    public static ThreadPoolStats convertCommon(ThreadPoolExecutor executor) {
        return ThreadPoolStats.builder().corePoolSize(executor.getCorePoolSize()).maximumPoolSize(executor.getMaximumPoolSize()).poolSize(executor.getPoolSize()).activeCount(executor.getActiveCount()).taskCount(executor.getTaskCount()).queueType(executor.getQueue().getClass().getSimpleName()).queueCapacity(executor.getQueue().size() + executor.getQueue().remainingCapacity()).queueSize(executor.getQueue().size()).queueRemainingCapacity(executor.getQueue().remainingCapacity()).completedTaskCount(executor.getCompletedTaskCount()).largestPoolSize(executor.getLargestPoolSize()).waitTaskCount(executor.getQueue().size()).build();
    }
}
