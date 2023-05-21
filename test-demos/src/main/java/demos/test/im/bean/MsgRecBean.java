package demos.test.im.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by xiedong
 * Date: 2023/5/21 15:08
 */
@Data
public class MsgRecBean extends BaseBean implements Serializable {

    private Integer fromuserid;//发送人ID

    private String msg;//消息


    public Byte code() {
        return 5;//业务指令
    }
}
