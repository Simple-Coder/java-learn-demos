package demos.test.im.group.bean;

import demos.test.im.BaseBean;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xiedong
 * Date: 2023/5/21 15:40
 */
@Data
public class GroupListResBean extends BaseBean implements Serializable {
    private List<GroupInfo> lists;

    @Override
    public Byte code() {
        return 6;
    }
}
