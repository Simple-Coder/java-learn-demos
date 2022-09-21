package metrics.monitor.collector;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import metrics.dto.ThreadPoolStats;
import metrics.em.CollectorTypeEnum;

/**
 * @author Redick01
 */
@Slf4j
public class InternalLogCollector extends AbstractCollector {

    @Override
    public void collect(ThreadPoolStats poolStats) {
        log.info("dynamic.tp metrics: {}", JSONUtil.toJsonStr(poolStats));
    }

    @Override
    public String type() {
        return CollectorTypeEnum.INTERNAL_LOGGING.name().toLowerCase();
    }
}
