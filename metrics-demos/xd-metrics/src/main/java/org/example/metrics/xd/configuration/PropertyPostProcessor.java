package org.example.metrics.xd.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertiesPropertySource;

import java.util.Properties;

/**
 * Created by xiedong
 * Date: 2022/8/13 16:27
 */
public class PropertyPostProcessor implements EnvironmentPostProcessor {
    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        Properties properties = new Properties();

        properties.put("endpoints.prometheus.enabled", true);
        properties.put("management.port", "${server.port}");

        //prometheus eureka服务发现
        properties.put("eureka.instance.metadata-map.prometheus.scrape", "true");
        properties.put("eureka.instance.metadata-map.prometheus.path", "/prometheus");
        properties.put("eureka.instance.metadata-map.prometheus.port", "${server.port}");
        properties.put("eureka.instance.instance-id", "${spring.cloud.client.ipAddress}:${server.port}");
        properties.put("eureka.instance.prefer-ip-address", "true");

//        properties.put("management.endpoint.health.show-details", "always");
//        properties.put("management.endpoint.prometheus.enabled", true);
//        properties.put("management.endpoint.jolokia.enabled", false);
//        properties.put("management.endpoint.env.post.enabled", false);
//        properties.put("management.metrics.web.client.request.autotime.enabled", false);
//        properties.put("spring.autoconfigure.exclude", "org.springframework.boot.actuate.autoconfigure.metrics.web.client.HttpClientMetricsAutoConfiguration");
//        properties.put("management.endpoints.web.exposure.include", "health,info,prometheus");

        PropertiesPropertySource propertiesPropertySource = new PropertiesPropertySource("hudongMetrics",properties);
        environment.getPropertySources().addLast(propertiesPropertySource);
    }
}

