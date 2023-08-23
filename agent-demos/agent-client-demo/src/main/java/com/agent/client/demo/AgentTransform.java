package com.agent.client.demo;

import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.utility.JavaModule;

import static net.bytebuddy.matcher.ElementMatchers.*;


/**
 * Created by xiedong
 * Date: 2023/8/23 21:25
 */
@Slf4j
public class AgentTransform implements AgentBuilder.Transformer {
    public static final String Mapping_Name = "org.springframework.web.bind.annotation";
    public static final String Mapping = "Mapping";

    @Override
    public DynamicType.Builder<?> transform(DynamicType.Builder<?> builder, TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule) {
        log.info("actuatName:{} to transform:{}", typeDescription.getActualName(), classLoader);
        DynamicType.Builder.MethodDefinition.ReceiverTypeDefinition<?> intercept =
                builder.method(not(isStatic())
                                .and(isAnnotatedWith(nameStartsWith(Mapping_Name).and(nameEndsWith(Mapping))
                                )))
                        .intercept(MethodDelegation.to(new SpringMvcIntercerptor()));
        return intercept;
    }
}
