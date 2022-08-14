package org.example.design.enums;

/**
 * Created by xiedong
 * Date: 2022/8/14 19:24
 */
public enum BizEnum {
    BIZ_XXX(1, "业务");

    private Integer code;
    private String name;

    BizEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }


}
