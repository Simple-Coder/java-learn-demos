package com.learn.boot.mapstruct;

/**
 * Created by xiedong
 * Date: 2022/8/31 22:07
 */
import java.math.BigDecimal;

public class UserConvertUtil {

    public static BigDecimal getStudentSumScore(Score score){
        return score.getSum();
    }
}
