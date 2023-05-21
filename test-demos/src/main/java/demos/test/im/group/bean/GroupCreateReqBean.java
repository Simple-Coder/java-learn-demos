package demos.test.im.group.bean;

import demos.test.im.BaseBean;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by xiedong
 * Date: 2023/5/21 15:35
 */
@Data
public class GroupCreateReqBean extends BaseBean implements Serializable {
    private Integer groupId;
    private String groupName;

    @Override
    public Byte code() {
        return 3;
    }
}
