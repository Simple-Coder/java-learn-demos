package demos.test.im.group.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by xiedong
 * Date: 2023/5/21 15:40
 */
@Data
public class GroupQuitResBean implements Serializable {
    private Integer code;
    private String msg;
}
