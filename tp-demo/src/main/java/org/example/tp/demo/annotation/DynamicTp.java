package org.example.tp.demo.annotation;

import java.lang.annotation.*;

/**
 * Created by xiedong
 * Date: 2022/11/3 21:06
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DynamicTp {

    /**
     * Thread pool name, has higher priority than @Bean annotated method name.
     *
     * @return name
     */
    String value() default "";
}