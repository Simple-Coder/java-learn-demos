package demos.test.im.single.bean;

import demos.test.im.BaseBean;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by xiedong
 * Date: 2023/5/21 15:07
 * 登录响应实体
 */
@Data
public class LoginResBean extends BaseBean implements Serializable {
    private Integer status;//响应状态，0登录成功，1登录失败
    private String msg;//响应信息
    private Integer userid;//用户ID

    public Byte code() {
        return 2;//业务指令
    }
}
