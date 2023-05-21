package demos.test.im.group.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by xiedong
 * Date: 2023/5/21 15:39
 */
@Data
public class GroupInfo implements Serializable {
    private Integer groupId;
    private String groupName;
}
