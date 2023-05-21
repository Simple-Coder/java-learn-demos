package demos.test.im.group.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xiedong
 * Date: 2023/5/21 15:40
 */
@Data
public class GroupListResBean implements Serializable {
    private List<GroupInfo> lists;
}
