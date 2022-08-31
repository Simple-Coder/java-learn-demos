package com.learn.boot.mapstruct;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Created by xiedong
 * Date: 2022/8/31 22:06
 */
@Data
@NoArgsConstructor
@ToString
public class StudentDTO {
    private String id;
    private String studentName;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    private BigDecimal sumScore;
    private BigDecimal avgScore;
}
