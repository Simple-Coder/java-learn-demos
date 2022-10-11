package com.learn.boot;

import com.learn.boot.config.UserConf;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by dongxie on 2022/9/13.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestA {
    @Autowired
    private UserConf userConf;

    @Test
    public void a1(){
        Long userId =210777169L;
        boolean contains = userConf.getUserIds().contains(userId);
        System.out.println();
    }
}
