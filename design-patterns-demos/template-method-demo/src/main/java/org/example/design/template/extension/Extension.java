package org.example.design.template.extension;

import org.example.design.template.common.BizScenario;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * Created by xiedong
 * Date: 2022/8/21 15:56
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Component
public @interface Extension {
    String bizId()  default BizScenario.DEFAULT_BIZ_ID;
    String useCase() default BizScenario.DEFAULT_USE_CASE;
    String scenario() default BizScenario.DEFAULT_SCENARIO;
}
