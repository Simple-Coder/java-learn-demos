package metrics.monitor.collector;

import metrics.dto.ThreadPoolStats;

/**
 * Created by xiedong
 * Date: 2022/9/21 22:20
 */
public interface MetricsCollector {
    /**
     * Collect key metrics.
     * @param poolStats ThreadPoolStats instance
     */
    void collect(ThreadPoolStats poolStats);

    /**
     * Collector type.
     * @return collector type
     */
    String type();

    /**
     * Judge collector type.
     * @param type collector type
     * @return true if the collector supports this type, else false
     */
    boolean support(String type);
}
