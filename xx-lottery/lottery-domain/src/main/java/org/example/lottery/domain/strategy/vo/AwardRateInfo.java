package org.example.lottery.domain.strategy.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Created by xiedong
 * Date: 2022/9/10 10:25
 * 奖品概率信息，奖品编号、库存、概率
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AwardRateInfo {
    // 奖品ID
    private String awardId;

    // 中奖概率
    private BigDecimal awardRate;

}
