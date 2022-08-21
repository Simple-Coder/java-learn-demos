package org.example.design.template;

import org.example.design.template.service.OpusServiceI;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by xiedong
 * Date: 2022/8/21 16:52
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestA {
    @Autowired
    private OpusServiceI opusServiceI;


    @Test
    public void a1(){
        Object test_opus_req_param = opusServiceI.getOpusList("Test opus req param");
        System.out.println("finish...."+test_opus_req_param);
    }
}
