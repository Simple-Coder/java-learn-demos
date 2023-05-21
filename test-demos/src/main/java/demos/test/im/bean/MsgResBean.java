package demos.test.im.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by xiedong
 * Date: 2023/5/21 15:08
 * 消息响应响应
 */
@Data
public class MsgResBean extends BaseBean implements Serializable {
    private Integer status;//响应状态，0发送成功，1发送失败
    private String msg;//响应信息

    public Byte code() {
        return 4;//业务指令
    }
}