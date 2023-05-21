package demos.test.im.group.bean;

import io.netty.channel.Channel;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by xiedong
 * Date: 2023/5/21 15:26
 */
@Data
//成员和连接通道的关系实体
public class GroupMember implements Serializable {
    private Integer userid;
    private Channel channel;
}