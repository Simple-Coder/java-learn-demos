package demos.test.im.single.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by xiedong
 * Date: 2023/5/21 15:06
 */
@Data
public abstract class BaseBean implements Serializable {
    private Integer tag = 1;//固定值，标识的是一个协议类型，不同协议对应不同的值

    public abstract Byte code();//业务指令抽象方法
}