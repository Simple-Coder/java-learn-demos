package demos.test.im.group.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by xiedong
 * Date: 2023/5/21 15:36
 */
@Data
public class GroupAddReqBean implements Serializable {
    private Integer userId;
    private Integer groupId;
}
