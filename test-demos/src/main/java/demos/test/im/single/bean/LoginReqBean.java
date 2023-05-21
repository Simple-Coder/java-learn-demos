package demos.test.im.single.bean;

import demos.test.im.BaseBean;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by xiedong
 * Date: 2023/5/21 15:06
 * 登录请求实体
 */
@Data
public class LoginReqBean extends BaseBean implements Serializable {
    private Integer userid;//用户ID
    private String username;//用户名称

    public Byte code() {
        return 1;//业务指令
    }
}
