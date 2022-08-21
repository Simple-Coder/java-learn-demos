package org.example.design.template.enums;

/**
 * Created by xiedong
 * Date: 2022/8/21 13:24
 */
public enum FeedType {
    SUBSCRIBE("1", "关注流"),
    HOMELIST("2", "动态流"),
    OPUELIST("3", "作品tab");

    private String code;
    private String name;

    FeedType(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }


}

