//package org.example.metrics.controller;
//
////import io.prometheus.client.spring.web.PrometheusTimeMethod;
//import cn.hutool.core.util.RandomUtil;
//import com.netflix.discovery.DiscoveryClient;
//import com.sohu.hd.metrics.custom.annotation.ControllerCost;
//import io.micrometer.core.instrument.Counter;
//import io.micrometer.core.instrument.Metrics;
//import io.micrometer.prometheus.PrometheusMeterRegistry;
//import io.prometheus.client.CollectorRegistry;
//import io.prometheus.client.Histogram;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.annotation.PostConstruct;
//
///**
// * Created by xiedong
// * Date: 2022/7/24
// */
//@RestController
//@Slf4j
//@RequestMapping("/metric1")
//public class TestContoller {
//    static final Counter userCounter = Metrics.counter("user.counter.total", "services", "demo");
//    Histogram user_hist_help = Histogram.build("user_histogram_test", "user_hist_help").buckets(100, 200, 300).create();
//    @Autowired
//    private PrometheusMeterRegistry meterRegistry;
//
//
//
////    @Autowired(required = false)
////    private DiscoveryClient discoveryClient;
//    //
//    @PostConstruct
//    public void init(){
//        CollectorRegistry prometheusRegistry = meterRegistry.getPrometheusRegistry();
//        prometheusRegistry.register(user_hist_help);
//
//
//        Histogram register = Histogram.build().buckets(.005, .01, .025, .05, .1, .25, .5, 1, 2.5, 5, 10).
//                name("uri_histogram.test-test")
//                .labelNames("application", "feed_service.test-test")  //aplication用于区分不同环境
//                .help("uri_histogram.test-test") //help不可少
//                .register(meterRegistry.getPrometheusRegistry());
//    }
//
//    //  http:localhost:8989/metric1/a
//    @GetMapping("/a")
//    @ControllerCost()
//    public String a() {
//        userCounter.increment();
//        Double gauge = Metrics.gauge("user.gauge.test", RandomUtil.randomDouble(10,10000));
//        user_hist_help.observe(RandomUtil.randomDouble(100,300));
////           new Thread(()->{
////           while (true){
////               Double gauge = Metrics.gauge("user.gauge.test", RandomUtil.randomDouble(10,10000));
////               System.out.println(gauge);
////               try {
////                   Thread.sleep(500);
////               } catch (InterruptedException e) {
////                   e.printStackTrace();
////               }
////           }
////        }).start();
//        return Thread.currentThread().getName()+gauge;
//    }
//
//
//}
