package com.learn.boot.mapstruct;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * Created by xiedong
 * Date: 2022/8/31 22:10
 */
@Data
@NoArgsConstructor
@ToString
public class UserDTO {
    private Integer id;
    private String name;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

}
