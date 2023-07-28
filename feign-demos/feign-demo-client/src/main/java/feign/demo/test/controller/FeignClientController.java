package feign.demo.test.controller;

import feign.demo.test.feign.TestFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by xiedong
 * Date: 2023/7/12 21:50
 */
@RestController
@RequestMapping("/test/feign")
@Slf4j
public class FeignClientController {
    @Resource
    private TestFeignClient testFeignClient;

    @GetMapping("/test1")
    public Object testFeign1(@RequestParam(required = true, name = "a") int a,
                             @RequestParam(required = true, name = "b") int b) {
        int addResult = testFeignClient.getAddResult(a, b);
        return "结果1：" + addResult;
    }

//    @GetMapping("/test2")
//    public Object testFeign2(@RequestParam(required = true, name = "a") int a,
//                             @RequestParam(required = true, name = "b") int b) {
//        AddReq addReq = new AddReq(a, b);
//
//        int addResult = testFeignClient.getAddResult(addReq);
//        return "结果2：" + addResult;
//    }
}
