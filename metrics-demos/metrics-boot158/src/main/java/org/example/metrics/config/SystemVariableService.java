package org.example.metrics.config;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xiedong
 * Date: 2022/10/12 21:44
 */
@Service
public class SystemVariableService {
    private Map<String, String> map = new HashMap<>();

    public String getSystemConfigValueByKey(String circuitForceFlag) {
        return map.get(circuitForceFlag);
    }

    public void putSystemConfigValueByKey(String circuitForceFlag, String val) {
        map.put(circuitForceFlag, val);
    }
}
