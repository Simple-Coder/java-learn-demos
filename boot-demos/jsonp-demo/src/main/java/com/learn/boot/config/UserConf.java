package com.learn.boot.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

/**
 * Created by xiedong
 * Date: 2022/9/29 20:53
 */
@Data
@Configuration
public class UserConf {
    @Value("#{'${feed.userIds}'.split(',')}")
    private Set<Long> userIds;
}
