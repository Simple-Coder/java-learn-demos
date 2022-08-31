package com.learn.boot.mapstruct;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by xiedong
 * Date: 2022/8/31 22:05
 */
@Data
@NoArgsConstructor
@ToString
public class Score {
    private BigDecimal mathScore;
    private BigDecimal englishScore;
    private BigDecimal chineseScore;

    public BigDecimal getSum(){
        return mathScore.add(englishScore).add(chineseScore).setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getAvg(){
        return (mathScore.add(englishScore).add(chineseScore)).divide(new BigDecimal("3"), RoundingMode.HALF_UP).setScale(2, RoundingMode.HALF_UP);
    }
}
