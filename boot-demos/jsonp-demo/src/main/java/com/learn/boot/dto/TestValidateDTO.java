package com.learn.boot.dto;

import com.learn.boot.validate.ValidGroup;
import lombok.Data;
//import org.hibernate.validator.constraints.Email;
//import org.hibernate.validator.constraints.NotBlank;
//
//import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Null;

/**
 * Created by dongxie on 2022/8/29.
 */
@Data
public class TestValidateDTO {
//    @Null(groups = ValidGroup.Crud.Create.class)
//    @NotNull(groups = ValidGroup.Crud.Update.class, message = "ID不能为空")
    private String id;

//    @Null(groups = ValidGroup.Crud.Create.class)
//    @NotNull(groups = ValidGroup.Crud.Update.class, message = "应用ID不能为空")
    private String appId;

//    @NotBlank(groups = ValidGroup.Crud.Create.class, message = "名字为必填项")
    private String name;

//    @Email(message = "请填写正确的邮箱地址")
    private String email;

    private int test1=-2;

//    private String id;

//    @EnumString(value = {"F","M"}, message="sex只允许为F或M")
//    private String sex;
//
//    @EnumString(value = {"F","M"}, message="1性别只允许为F或M")
//    private String sex1;


//    @Length(min = 6,max = 12,message = "appId长度必须位于6到12之间")
//    private String appId;
//
//    @NotBlank(message = "名字为必填项")
//    private String name;
//
//    private String sex;
//
//    @NotEmpty(message = "级别不能为空")
//    private String level;
}
