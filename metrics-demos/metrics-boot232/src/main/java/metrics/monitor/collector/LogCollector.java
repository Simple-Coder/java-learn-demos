package metrics.monitor.collector;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import metrics.dto.ThreadPoolStats;

/**
 * Created by xiedong
 * Date: 2022/9/21 22:24
 */
@Slf4j
public class LogCollector extends AbstractCollector {

    @Override
    public void collect(ThreadPoolStats threadPoolStats) {
        String metrics = JSONUtil.toJsonStr(threadPoolStats);
        log.info(metrics);
//        if (LogHelper.getMonitorLogger() == null) {
//            log.error("Cannot find monitor logger...");
//            return;
//        }
//        LogHelper.getMonitorLogger().info("{}", metrics);
    }

    @Override
    public String type() {
//        return CollectorTypeEnum.LOGGING.name().toLowerCase();
        return "test";
    }
}