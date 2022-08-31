package com.learn.boot.mapstruct;

/**
 * Created by xiedong
 * Date: 2022/8/31 22:06
 */
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", imports = {UserConvertUtil.class})
public interface UserConvert {
    @Mappings({
            @Mapping(target = "studentName", source = "userDTO.name"),
            @Mapping(target = "id", expression = "java(String.valueOf(userDTO.getId()))"),
            @Mapping(target = "createTime", source = "userDTO.createTime"),
            @Mapping(target = "updateTime", source = "userDTO.updateTime"),
            @Mapping(target = "sumScore", expression = "java(UserConvertUtil.getStudentSumScore(score))"),
            @Mapping(target = "avgScore", expression = "java(score.getAvg())")
    })
    StudentDTO toStudent(UserDTO userDTO, Score score);
}