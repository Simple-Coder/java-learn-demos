package com.agent.client.demo;

import io.micrometer.core.instrument.Metrics;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.implementation.bind.annotation.*;

import java.lang.reflect.Method;
import java.util.concurrent.Callable;

/**
 * Created by xiedong
 * Date: 2023/8/23 21:34
 */
@Slf4j
public class SpringMvcIntercerptor {
    @RuntimeType
    public Object interceptor(@This Object targetObj, @Origin Method method, @AllArguments Object[] allArgs, @Super Object targetObject2, @SuperCall Callable<?> zuper) {
        log.info("targetObj:{} before controller", targetObj);
        System.out.println("targetObj" + targetObj);
        Object call = null;
        try {
            Metrics.counter("java_mid_server_test", "method", method.getName()).increment();
            call = zuper.call();
        } catch (Exception e) {
            log.error("call error", e);
        }
        log.info("targetObj:{} end controller", targetObj);
        return call;
    }
}
