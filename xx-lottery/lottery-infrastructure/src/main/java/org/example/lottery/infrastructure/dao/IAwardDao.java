package org.example.lottery.infrastructure.dao;

import org.apache.ibatis.annotations.Mapper;
import org.example.lottery.infrastructure.po.Award;

/**
 * Created by xiedong
 * Date: 2022/9/10 11:03
 */
@Mapper
public interface IAwardDao {
    Award queryAwardInfo(String awardId);
}
