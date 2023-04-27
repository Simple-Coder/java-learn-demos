package org.example.tp.current;

import cn.hutool.core.map.MapUtil;
import lombok.SneakyThrows;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xiedong
 * Date: 2022/9/24 18:43
 */

public class OuterService {
    @SneakyThrows
    public Map getAddressInfos(String name, long sleepTime) {
        System.out.println("treadName:" + Thread.currentThread().getName() + " getAddressInfos sleepTime:" + sleepTime);
        HashMap<String, String> infos = MapUtil.newHashMap();
        infos.put(name, name);
        infos.put("address", "test");
        Thread.sleep(sleepTime);
        return infos;
    }

    @SneakyThrows
    public Map getSchoolInfos(String name, long sleepTime) {
        System.out.println("treadName:" + Thread.currentThread().getName() + " getSchoolInfos sleepTime:" + sleepTime);
        HashMap<String, String> infos = MapUtil.newHashMap();
        infos.put(name, name);
        infos.put("mother", "test");
        Thread.sleep(sleepTime);
        return infos;
    }

    @SneakyThrows
    public Map getMontherInfos(String name, long sleepTime) {
        System.out.println("treadName:" + Thread.currentThread().getName() + " getMontherInfos sleepTime:" + sleepTime);
        HashMap<String, String> infos = MapUtil.newHashMap();
        infos.put(name, name);
        infos.put("mother", "test");
        Thread.sleep(sleepTime);
        return infos;
    }

}
