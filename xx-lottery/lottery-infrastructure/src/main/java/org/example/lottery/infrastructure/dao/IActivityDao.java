package org.example.lottery.infrastructure.dao;

import org.apache.ibatis.annotations.Mapper;
import org.example.lottery.infrastructure.po.Activity;

/**
 * Created by xiedong
 * Date: 2022/9/8 21:32
 */
@Mapper
public interface IActivityDao {
    void insert(Activity req);

    Activity queryActivityById(Long activityId);
}
