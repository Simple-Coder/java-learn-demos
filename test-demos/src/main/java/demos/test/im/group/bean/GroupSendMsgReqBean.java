package demos.test.im.group.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by xiedong
 * Date: 2023/5/21 15:38
 */
@Data
public class GroupSendMsgReqBean implements Serializable {
    private Integer fromuserid;
    private Integer togroupid;
    private String msg;
}
