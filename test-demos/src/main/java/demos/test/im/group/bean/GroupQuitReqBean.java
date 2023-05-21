package demos.test.im.group.bean;

import demos.test.im.BaseBean;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by xiedong
 * Date: 2023/5/21 15:37
 */
@Data
public class GroupQuitReqBean extends BaseBean implements Serializable {
    private Integer userId;
    private Integer groupId;

    @Override
    public Byte code() {
        return 9;
    }
}
