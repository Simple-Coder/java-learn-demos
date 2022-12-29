package org.example.tp.limiter;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.TimeUnit;

/**
 * @Author xiedong
 * @Date 2022/12/13 12:18
 **/
public class LimiterTest {
    public static void main(String[] args) {
        RateLimiter rateLimiter = RateLimiter.create(1.0);
        double rate = rateLimiter.getRate();

        System.out.println(rate);
        int count = 0;
        while (true) {
            count++;

            double acquire = rateLimiter.acquire(2);
            System.out.println("第" + count + "次等待" + acquire);
            if (acquire > 2) {
                break;
            }

        }

        System.out.println();

    }
}
