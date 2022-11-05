//package com.learn.boot.config;
//
//import org.springframework.beans.BeansException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.support.BeanDefinitionBuilder;
//import org.springframework.beans.factory.support.DefaultListableBeanFactory;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.ApplicationContextAware;
//import org.springframework.stereotype.Component;
//@Component
//public class ContextAware implements ApplicationContextAware {
//    @Autowired
//    DefaultListableBeanFactory beanFactory;
//
//    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
//        applicationContext.get
//
//
//        Class<? extends Compont2> c2 = Compont2.class;
//        beanFactory.createBean(c2);
//        String name = c2.getName();
//
//        BeanDefinitionBuilder beanDefinitionBuilder1 = BeanDefinitionBuilder.genericBeanDefinition();
//
//
//        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(c2);
//        beanFactory.registerBeanDefinition(name, beanDefinitionBuilder.getBeanDefinition());
//    }
//}
