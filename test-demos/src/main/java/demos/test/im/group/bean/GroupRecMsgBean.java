package demos.test.im.group.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by xiedong
 * Date: 2023/5/21 15:42
 */
@Data
public class GroupRecMsgBean implements Serializable {
    private Integer fromuserid;
    private String msg;
}
