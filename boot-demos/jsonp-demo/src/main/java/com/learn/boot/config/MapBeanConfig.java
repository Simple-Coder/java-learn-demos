package com.learn.boot.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * Created by xiedong
 * Date: 2022/8/30 21:29
 * 映射配置map 使用
 */
@Data
@Configuration
public class MapBeanConfig {
    //    @Value("#{${test.route.v2:'{}'}}")
//    @Value("#{${populate.map:{}}}")
//    @Value("#{${populate.map:null}}}")
    @Value("#{${populate.map:{k1:'v1'}}}")
    private Map<String, String> urls;
}
