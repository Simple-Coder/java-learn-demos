package org.example.design;

import org.example.design.service.SubscribeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by xiedong
 * Date: 2022/8/14 20:20
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class FilterChainTest {
    @Autowired
    private SubscribeService subscribeService;

    @Test
    public void a1(){
        Object req_test = subscribeService.getSubscribeList("req test");
        System.out.println(req_test);
    }
}
