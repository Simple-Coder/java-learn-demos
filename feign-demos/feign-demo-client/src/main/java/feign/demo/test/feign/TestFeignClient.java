package feign.demo.test.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by xiedong
 * Date: 2023/7/12 21:50
 */
@FeignClient(name = "FEIGN-DEMO-SERVER-SERVICE")
public interface TestFeignClient {

    @GetMapping("/feign/server/add")
    int getAddResult(@RequestParam("a") int a, @RequestParam("b") int b);

//    int getAddResult(AddReq addReq);

}
