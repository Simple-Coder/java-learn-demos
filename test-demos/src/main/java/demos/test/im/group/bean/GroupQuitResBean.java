package demos.test.im.group.bean;

import demos.test.im.BaseBean;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by xiedong
 * Date: 2023/5/21 15:40
 */
@Data
public class GroupQuitResBean extends BaseBean implements Serializable {
    private Integer code;
    private String msg;

    @Override
    public Byte code() {
        return 10;
    }
}
