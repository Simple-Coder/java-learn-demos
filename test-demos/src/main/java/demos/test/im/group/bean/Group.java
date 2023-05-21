package demos.test.im.group.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiedong
 * Date: 2023/5/21 15:26
 */
@Data
public class Group implements Serializable {
    private String groupName;
    private List<GroupMember> members = new ArrayList<GroupMember>();
}