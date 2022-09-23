package org.example.tp.opt;

import cn.hutool.core.lang.Opt;

import java.util.*;

/**
 * Created by dongxie on 2022/9/23.
 */
public class Test {
    public static void main(String[] args) {
        Response<List<Integer>> readHomeList = Response.failReport(500, "getHomeListFallback");
        boolean present = Opt.ofNullable(readHomeList).filter(Response::success).isPresent();

//        Optional.ofNullable(readHomeList).filter(Response::success).;
        List<Integer> integers = Opt.ofNullable(readHomeList).filter(Response::success).map(Response::getData).orElse(Arrays.asList(1, 2, 3, 45));

        System.out.println();


    }
}
