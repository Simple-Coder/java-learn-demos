package demos.test.im.group.bean;

import demos.test.im.BaseBean;
import demos.test.im.MapUtils;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by xiedong
 * Date: 2023/5/21 15:38
 */
@Data
public class GroupSendMsgReqBean extends BaseBean implements Serializable {
    private Integer fromuserid;
    private Integer togroupid;
    private String msg;

    @Override
    public Byte code() {
        return MapUtils.groupSendMsgReq;
    }
}
