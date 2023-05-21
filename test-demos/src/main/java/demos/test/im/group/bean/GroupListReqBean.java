package demos.test.im.group.bean;

import demos.test.im.BaseBean;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by xiedong
 * Date: 2023/5/21 15:36
 */
@Data
public class GroupListReqBean extends BaseBean implements Serializable {
    private String type;

    @Override
    public Byte code() {
        return 5;
    }
}
