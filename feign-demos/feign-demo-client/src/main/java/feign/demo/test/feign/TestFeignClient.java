package feign.demo.test.feign;

import feign.demo.test.bean.AddReq;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
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

    @GetMapping("/feign/server/add1")
    int getAddResult1(@SpringQueryMap AddReq addReq);

//    int getAddResult(AddReq addReq);

}
