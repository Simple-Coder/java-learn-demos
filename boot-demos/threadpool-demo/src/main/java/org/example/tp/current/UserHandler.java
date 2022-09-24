package org.example.tp.current;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.TimeInterval;
import org.example.tp.GlobalTp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by xiedong
 * Date: 2022/9/24 18:35
 */
public class UserHandler {
    public void handle(UserContext ctx) throws InterruptedException {
        TimeInterval timer = DateUtil.timer();
        try {
            this.loadData(ctx);

            this.preFilter(ctx);

            this.dohandle(ctx);

            this.postFilter(ctx);

            ctx.setSuccess(true);
        } catch (Exception e) {
            throw e;
        }
        System.out.println(Thread.currentThread().getName() + "cost:" + timer.intervalPretty());
    }

    private void dohandle(UserContext ctx) throws InterruptedException {
        OuterService outerService = new OuterService();
        GlobalTp globalTp = new GlobalTp();
        System.out.println(Thread.currentThread().getName() + "do handle start...");

        String reqName = ctx.getReqName();
        RspBean rspBean = ctx.getRspBean();
        ThreadPoolExecutor taskExecutor = globalTp.getTaskExecutor();


        CountDownLatch countDownLatch = new CountDownLatch(3);
        List<Callable<Void>> tasks = new ArrayList<>();

        tasks.add(() -> {
            Map addressInfos = outerService.getAddressInfos(reqName);
            rspBean.setAddressInfos(addressInfos);
            countDownLatch.countDown();
            return null;
        });

        tasks.add(() -> {
            Map schoolInfos = outerService.getSchoolInfos(reqName);
            rspBean.setSchoolInfos(schoolInfos);
            countDownLatch.countDown();
            return null;
        });
        tasks.add(() -> {
            Map montherInfos = outerService.getMontherInfos(reqName);
            rspBean.setMontherInfos(montherInfos);
            countDownLatch.countDown();
            return null;
        });

        taskExecutor.invokeAll(tasks, 1, TimeUnit.SECONDS);
//        CountDownLatch countDownLatch = new CountDownLatch(3);
//        //1.addressInfos
//        taskExecutor.submit(() -> {
//            Map addressInfos = outerService.getAddressInfos(reqName);
//            rspBean.setAddressInfos(addressInfos);
//            countDownLatch.countDown();
//        });
//        //2.schoolInfos
//        taskExecutor.submit(() -> {
//            Map schoolInfos = outerService.getSchoolInfos(reqName);
//            rspBean.setSchoolInfos(schoolInfos);
//            countDownLatch.countDown();
//        });
//
//
//
//
//        //3.montherInfos
//        taskExecutor.submit(() -> {
//            Map montherInfos = outerService.getMontherInfos(reqName);
//            rspBean.setMontherInfos(montherInfos);
//            countDownLatch.countDown();
//        });

        rspBean.setRspName("test");
        System.out.println(Thread.currentThread().getName() + "do handle end...");

        try {
            //不能抛上去，否则会影响其他流程执行
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void postFilter(UserContext ctx) {
        System.out.println(Thread.currentThread().getName() + "postfilter");
    }

    private void preFilter(UserContext ctx) {
        System.out.println(Thread.currentThread().getName() + "prefilter...");
    }

    private void loadData(UserContext ctx) {
        System.out.println(Thread.currentThread().getName() + "load data");
        ctx.setRspBean(new RspBean());
    }
}
