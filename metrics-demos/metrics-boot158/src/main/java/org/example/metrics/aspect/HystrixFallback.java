package org.example.metrics.aspect;

import java.lang.annotation.*;

/**
 * Created by xiedong
 * Date: 2022/10/11 20:59
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@Documented
public @interface HystrixFallback {
    boolean fallback() default false;
}
