package metrics.monitor.collector;

/**
 * Created by xiedong
 * Date: 2022/9/21 22:21
 */
public abstract class AbstractCollector implements MetricsCollector {

    @Override
    public boolean support(String type) {
        return this.type().equalsIgnoreCase(type);
    }
}
