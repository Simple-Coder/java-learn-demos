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
    private long sleepTime;

    public UserHandler(long sleepTime) {
        this.sleepTime = sleepTime;
    }

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
        } finally {
            System.out.println(Thread.currentThread().getName() + "cost:" + timer.intervalPretty());
        }
    }

    private void dohandle(UserContext ctx) throws InterruptedException {
        OuterService outerService = new OuterService();
        GlobalTp globalTp = new GlobalTp();
        System.out.println(Thread.currentThread().getName() + "do handle start...");

        String reqName = ctx.getReqName();
        RspBean rspBean = ctx.getRspBean();
        ThreadPoolExecutor taskExecutor = globalTp.getTaskExecutor();


        List<Callable<Void>> tasks = new ArrayList<>();

        tasks.add(() -> {
            System.out.println("start setAddressInfos");
            Map addressInfos = outerService.getAddressInfos(reqName, sleepTime);
            rspBean.setAddressInfos(addressInfos);
            System.out.println("end setAddressInfos");
            return null;
        });

        tasks.add(() -> {
            System.out.println("start setSchoolInfos");
            Map schoolInfos = outerService.getSchoolInfos(reqName, sleepTime * 2);
            rspBean.setSchoolInfos(schoolInfos);
            System.out.println("end setSchoolInfos");
            return null;
        });
        tasks.add(() -> {
            System.out.println("start setMontherInfos");
            Map montherInfos = outerService.getMontherInfos(reqName, sleepTime * 3);
            rspBean.setMontherInfos(montherInfos);
            System.out.println("end setMontherInfos");
            return null;
        });

        taskExecutor.invokeAll(tasks, 1, TimeUnit.MILLISECONDS);

        rspBean.setRspName("test");
        System.out.println(Thread.currentThread().getName() + "do handle end...");
    }

    private void postFilter(UserContext ctx) {
//        System.out.println(Thread.currentThread().getName() + "postfilter");
    }

    private void preFilter(UserContext ctx) {
//        System.out.println(Thread.currentThread().getName() + "prefilter...");
    }

    private void loadData(UserContext ctx) {
//        System.out.println(Thread.currentThread().getName() + "load data");
        ctx.setRspBean(new RspBean());
    }
}
