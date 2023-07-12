package feign.demo.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Created by xiedong
 * Date: 2023/7/12 21:47
 */
@SpringBootApplication
@EnableEurekaServer
public class FeignEurekaApplication {
    public static void main(String[] args) {
        SpringApplication.run(FeignEurekaApplication.class, args);
    }
}
