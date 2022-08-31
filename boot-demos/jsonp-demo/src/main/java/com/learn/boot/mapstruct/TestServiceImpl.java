package com.learn.boot.mapstruct;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Created by xiedong
 * Date: 2022/8/31 22:09
 */
@Service
public class TestServiceImpl implements TestService{

    @Resource
    private UserConvert userConvert;

    @Override
    public String test() {
        UserDTO userDTO = new UserDTO();
        Score score = new Score();

        userDTO.setId(1001);
        userDTO.setCreateTime(LocalDateTime.now());
        userDTO.setUpdateTime(LocalDateTime.now());
        userDTO.setName("小刘");

        score.setChineseScore(new BigDecimal("99.9"));
        score.setMathScore(new BigDecimal("67.2"));
        score.setEnglishScore(new BigDecimal("89.9"));
        StudentDTO studentDTO = userConvert.toStudent(userDTO, score);

        return studentDTO.toString();
    }
}
