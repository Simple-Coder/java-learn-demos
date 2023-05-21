package demos.test.im.group.bean;

import demos.test.im.BaseBean;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by xiedong
 * Date: 2023/5/21 15:42
 */
@Data
public class GroupRecMsgBean extends BaseBean implements Serializable {
    private Integer fromuserid;
    private String msg;

    @Override
    public Byte code() {
        return 15;
    }
}
