package org.example.tp;

import com.alibaba.fastjson.JSONObject;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by dongxie on 2022/9/29.
 */
public class TestSet {
    public static void main(String[] args) {
        Set<Long> a1= new HashSet<>();
        a1.add(1L);
        a1.add(2L);
        a1.add(3L);

        JSONObject jsonObject = new JSONObject();

        int status = jsonObject.getIntValue("status");


        Long a2 = 1L;
        boolean contains = a1.contains(a2);
        System.out.println();
    }
}
