package feign.demo.test.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by xiedong
 * Date: 2023/7/12 21:58
 */
@RestController
@Slf4j
@RequestMapping("/feign/server")
public class FeignServerController {
    @GetMapping("/add")
    public Object add(@RequestParam(required = true, name = "a") int a,
                       @RequestParam(required = true, name = "b") int b) {
        return a + b;
    }

    @GetMapping("/add1")
    public Object add1(@RequestParam(required = true, name = "a") int a,
                       @RequestParam(required = true, name = "b") int b) {
        return a + b;
    }
}
