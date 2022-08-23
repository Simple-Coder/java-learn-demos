package org.example.design.template;

import cn.hutool.core.collection.CollUtil;

import java.util.ArrayList;
import java.util.List;

public class TestB {
    public static void main(String[] args) {
        List<Integer> a = new ArrayList<>();
        a.add(1);
        a.add(6);
        a.add(4);
        a.add(3);
        a.add(2);
        //修改原数组
        List<Integer> sort = CollUtil.sort(a, (n, m) -> m.compareTo(n));
        List<Integer> sor1t = CollUtil.sort(a, (m, n) -> m.compareTo(n));
        System.out.println();
    }
}
