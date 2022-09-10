package org.example.lottery.domain.strategy.service.algorithm.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.lottery.domain.strategy.service.algorithm.BaseAlgorithm;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.List;

/**
 * Created by xiedong
 * Date: 2022/9/10 10:30
 */
@Service
@Slf4j
public class SingleRateRandomDrawAlgorithm extends BaseAlgorithm {
    @Override
    public String randomDraw(Long strategyId, List<String> excludeAwardIds) {
        // 获取策略对应的元祖
        String[] rateTuple = super.rateTupleMap.get(strategyId);
        assert rateTuple != null;

        // 随机索引
        int randomVal = new SecureRandom().nextInt(100) + 1;
        int idx = super.hashIdx(randomVal);

        // 返回结果
        String awardId = rateTuple[idx];
        if (excludeAwardIds.contains(awardId)) return "未中奖";

        return awardId;
    }
}
