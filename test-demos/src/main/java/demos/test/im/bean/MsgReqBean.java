package demos.test.im.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by xiedong
 * Date: 2023/5/21 15:07
 * 消息发送实体
 */
@Data
public class MsgReqBean extends BaseBean implements Serializable {

    private Integer fromuserid;//发送人ID

    private Integer touserid;//接受人ID

    private String msg;//发送消息


    public Byte code() {
        return 3;//业务指令
    }
}