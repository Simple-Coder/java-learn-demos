package com.agent.client.demo;

import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.utility.JavaModule;

/**
 * Created by xiedong
 * Date: 2023/8/23 21:48
 */
@Slf4j
public class DemoListener implements AgentBuilder.Listener {
    @Override
    public void onDiscovery(String s, ClassLoader classLoader, JavaModule javaModule, boolean b) {
        System.out.println("onDiscovery:" + s);
        log.info("onDiscovery:{}", s);
    }

    @Override
    public void onTransformation(TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule, boolean b, DynamicType dynamicType) {
        System.out.println("onTransformation:" + typeDescription.getActualName());
        log.info("onTransformation:{}", typeDescription.getActualName());
    }

    @Override
    public void onIgnored(TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule, boolean b) {
        System.out.println("onIgnored:" + typeDescription.getActualName());
        log.info("onIgnored:{}", typeDescription.getActualName());
    }

    @Override
    public void onError(String s, ClassLoader classLoader, JavaModule javaModule, boolean b, Throwable throwable) {
        System.out.println("onError:" + s);
        log.info("onError:{}", s);
    }

    @Override
    public void onComplete(String s, ClassLoader classLoader, JavaModule javaModule, boolean b) {
        System.out.println("onComplete:" + s);
        log.info("onComplete:{}", s);
    }
}
