package metrics.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by xiedong
 * Date: 2022/7/24
 */
@RestController
@Slf4j
@RequestMapping("/metric1")
public class TestContoller {
    //  http:localhost:8989/metric1/a
    // http://127.0.0.1:9998/metrics

    @Resource(name = "globalTPExecutor")
    private ThreadPoolExecutor globalTPExecutor;

    @GetMapping("/a/{core}")
    public String a(@PathVariable int core) {
        int before = globalTPExecutor.getCorePoolSize();
        globalTPExecutor.execute(() -> {
            System.out.println(Thread.currentThread().getName());
        });
        globalTPExecutor.setCorePoolSize(core);
//        DtpExecutor dtpExecutor = DtpRegistry.getDtpExecutor("dtpExecutor1");
//        dtpExecutor.execute(() -> System.out.println("test"));

        return before + Thread.currentThread().getName() + globalTPExecutor.getCorePoolSize();
    }
}
