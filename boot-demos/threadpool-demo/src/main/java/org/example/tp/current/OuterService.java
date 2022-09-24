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
    public Map getAddressInfos(String name) {
        HashMap<String, String> infos = MapUtil.newHashMap();
        infos.put(name, name);
        infos.put("address", "test");
        Thread.sleep(1000);
        return infos;
    }

    @SneakyThrows
    public Map getSchoolInfos(String name) {
        HashMap<String, String> infos = MapUtil.newHashMap();
        infos.put(name, name);
        infos.put("mother", "test");
        Thread.sleep(2000);
        return infos;
    }

    @SneakyThrows
    public Map getMontherInfos(String name) {
        HashMap<String, String> infos = MapUtil.newHashMap();
        infos.put(name, name);
        infos.put("mother", "test");
        Thread.sleep(3000);
        return infos;
    }

}
