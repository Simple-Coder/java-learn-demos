package org.example.design.template.extension.register;

import org.example.design.template.extension.Extension;
import org.example.design.template.extension.ExtensionPointI;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by xiedong
 * Date: 2022/8/21 15:57
 */
@Component
public class ExtensionBootstrap implements ApplicationContextAware {

    @Resource
    private ExtensionRegister extensionRegister;

    private ApplicationContext applicationContext;

    @PostConstruct
    public void init(){
        Map<String, Object> extensionBeans = applicationContext.getBeansWithAnnotation(Extension.class);
        extensionBeans.values().forEach(
                extension -> extensionRegister.doRegistration((ExtensionPointI) extension)
        );
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
