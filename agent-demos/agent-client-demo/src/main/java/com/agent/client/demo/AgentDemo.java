package com.agent.client.demo;

import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.matcher.ElementMatchers;

import java.lang.instrument.Instrumentation;

/**
 * Created by xiedong
 * Date: 2023/8/23 21:09
 */
@Slf4j
public class AgentDemo {
    public static final String Rest_Controller_Name = "org.springframework.web.bind.annotation.RestController";

    public static void premain(String args, Instrumentation instrumentation) {
        log.info("进入agent入口，参数:{}", args);
        AgentBuilder builder = new AgentBuilder.Default()
                .ignore(ElementMatchers.nameStartsWith("net.bytebuddy")
                        .or(ElementMatchers.nameStartsWith("org.apache")))
                .type(ElementMatchers.isAnnotatedWith(
                        ElementMatchers.named(Rest_Controller_Name)
                ))
                .transform(new AgentTransform())
                .with(new DemoListener());

        builder.installOn(instrumentation);
    }
}
