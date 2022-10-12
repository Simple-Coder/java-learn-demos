package org.example.metrics.config;

import com.netflix.config.PollResult;
import com.netflix.config.PolledConfigurationSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xiedong
 * Date: 2022/10/12 21:42
 */
@Component
public class DynamicConfigSource implements PolledConfigurationSource {

    private static final Logger logger = LoggerFactory.getLogger(DynamicConfigSource.class);

    @Autowired
    private SystemVariableService systemVariableService;

    @Override

    public PollResult poll(boolean initial, Object checkPoint) throws Exception {

        Map<String, Object> complete = new HashMap<String, Object>();

/** * 配置中心配置了对应的key/value。其中commandKey是我指定的具体接口。 */

//断路器开关标记，1：强制打开断路器，拒绝所有请求 2：强制关闭断路器，放行所有请求

        String circuitForceFlag = systemVariableService.getSystemConfigValueByKey("circuitForceFlag");

        if (circuitForceFlag != null)

        {

            if ("1".equals(circuitForceFlag))

            {

//hystrixSetter.withCircuitBreakerForceOpen(true);

//调用者执行的超时时间

complete.put("hystrix.command.default.circuitBreaker.forceOpen", false);
//complete.put("hystrix.command.default.circuitBreaker.forceOpen", false);

                complete.put("hystrix.command.default.execution.isolation.thread.timeoutInMilliseconds", 500);

            }

            if ("0".equals(circuitForceFlag))

            {

//hystrixSetter.withCircuitBreakerForceClosed(true)

//调用者执行的超时时间

complete.put("hystrix.command.default.circuitBreaker.forceOpen", true);
//complete.put("hystrix.command.default.circuitBreaker.forceOpen", true);

                complete.put("hystrix.command.default.execution.isolation.thread.timeoutInMilliseconds", 1000);

            }

        }

        System.out.println(String.format("poll:%s", complete));

        return PollResult.createFull(complete);

    }

}