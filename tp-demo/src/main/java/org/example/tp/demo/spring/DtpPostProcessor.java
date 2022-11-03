package org.example.tp.demo.spring;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.example.tp.demo.DynamicTp;
import org.example.tp.demo.core.DtpRegistry;
import org.example.tp.demo.dto.ExecutorWrapper;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.core.type.MethodMetadata;
import org.springframework.lang.NonNull;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.Collections;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * BeanPostProcessor that handles all related beans managed by Spring.
 *
 * @author yanhom
 * @since 1.0.0
 **/
@Slf4j
public class DtpPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessAfterInitialization(@NonNull Object bean, @NonNull String beanName) throws BeansException {

        if (!(bean instanceof ThreadPoolExecutor) && !(bean instanceof ThreadPoolTaskExecutor)) {
            return bean;
        }

        ApplicationContext applicationContext = ApplicationContextHolder.getInstance();
        String dtpAnnotationVal;
        try {
            DynamicTp dynamicTp = applicationContext.findAnnotationOnBean(beanName, DynamicTp.class);
            if (Objects.nonNull(dynamicTp)) {
                dtpAnnotationVal = dynamicTp.value();
            } else {
                BeanDefinitionRegistry registry = (BeanDefinitionRegistry) applicationContext;
                AnnotatedBeanDefinition annotatedBeanDefinition = (AnnotatedBeanDefinition) registry.getBeanDefinition(beanName);
                MethodMetadata methodMetadata = (MethodMetadata) annotatedBeanDefinition.getSource();
                if (Objects.isNull(methodMetadata) || !methodMetadata.isAnnotated(DynamicTp.class.getName())) {
                    return bean;
                }
                dtpAnnotationVal = Optional.ofNullable(methodMetadata.getAnnotationAttributes(DynamicTp.class.getName())).orElse(Collections.emptyMap()).getOrDefault("value", "").toString();
            }
        } catch (NoSuchBeanDefinitionException e) {
            log.error("There is no bean with the given name {}", beanName, e);
            return bean;
        }

        String poolName = StrUtil.isNotBlank(dtpAnnotationVal) ? dtpAnnotationVal : beanName;
        if (bean instanceof ThreadPoolTaskExecutor) {
            ThreadPoolTaskExecutor taskExecutor = (ThreadPoolTaskExecutor) bean;
            registerCommon(poolName, taskExecutor.getThreadPoolExecutor());
        } else {
            registerCommon(poolName, (ThreadPoolExecutor) bean);
        }
        return bean;
    }


    private void registerCommon(String poolName, ThreadPoolExecutor executor) {
        ExecutorWrapper wrapper = new ExecutorWrapper(poolName, executor);
        DtpRegistry.registerCommon(wrapper, "beanPostProcessor");
    }
}
