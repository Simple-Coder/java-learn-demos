package org.example.lottery.infrastructure.po;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by xiedong
 * Date: 2022/9/10 11:02
 * 策略明细
 */
@Data
public class StrategyDetail {
    // 自增ID
    private String id;

    // 策略ID
    private Long strategyId;

    // 奖品ID
    private String awardId;

    // 奖品数量
    private String awardCount;

    // 中奖概率
    private BigDecimal awardRate;

    // 创建时间
    private String createTime;

    // 修改时间
    private String updateTime;
}
