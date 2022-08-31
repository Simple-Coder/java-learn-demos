package org.example.metrics;

//import com.sohu.hd.metrics.custom.annotation.EnableControllerCost;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

/**
 * Created by xiedong
 * Date: 2022/8/13 16:19
 */
@SpringBootApplication
@EnableHystrix
//@EnableDiscoveryClient
//@EnableControllerCost
public class BootServer9999 {
    public static void main(String[] args) {
        SpringApplication.run(BootServer9999.class, args);
    }
}
