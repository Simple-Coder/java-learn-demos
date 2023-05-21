package demos.test.im.group.bean;

import demos.test.im.BaseBean;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xiedong
 * Date: 2023/5/21 15:41
 */
@Data
public class GroupMemberResBean extends BaseBean implements Serializable {
    private Integer code;
    private String msg;
    List<Integer> lists;

    @Override
    public Byte code() {
        return 12;
    }
}
