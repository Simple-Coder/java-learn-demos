package demos.test.im.group.bean;

import demos.test.im.BaseBean;
import demos.test.im.group.bean.*;
import demos.test.im.single.bean.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xiedong
 * Date: 2023/5/21 15:53
 */
public class MapGroupUtils {
    private static Byte codeLoginReq = 1;

    private static Byte codeLoginRes = 2;

    private static Byte groupCreateReq = 3;

    private static Byte groupCreateRes = 4;
    private static Byte groupListReq = 5;
    private static Byte groupListRes = 6;
    private static Byte groupAddReq = 7;
    private static Byte groupAddRes = 8;
    private static Byte groupQuitReq = 9;
    private static Byte groupQuitRes = 10;
    private static Byte groupMemberReq = 11;
    private static Byte groupMemberRes = 12;
    private static Byte groupSendMsgReq = 13;
    private static Byte groupSendMsgRes = 14;
    private static Byte groupRecMsg = 15;


    private static Map<Byte, Class<? extends BaseBean>> map = new HashMap<Byte, Class<? extends BaseBean>>();

    static {
        //登录的请求和响应实体
        map.put(codeLoginReq, LoginReqBean.class);
        map.put(codeLoginRes, LoginResBean.class);

        //创建群组的请求和响应实体
        map.put(groupCreateReq, GroupCreateReqBean.class);
        map.put(groupCreateRes, GroupCreateResBean.class);

        //查看群组的请求和响应实体
        map.put(groupListReq, GroupListReqBean.class);
        map.put(groupListRes, GroupListResBean.class);

        //加入群组的请求和响应实体
        map.put(groupAddReq, GroupAddReqBean.class);
        map.put(groupAddRes, GroupAddResBean.class);

        //退出群组的请求和响应实体
        map.put(groupQuitReq, GroupQuitReqBean.class);
        map.put(groupQuitRes, GroupQuitResBean.class);

        //查看成员列表的请求和响应实体
        map.put(groupMemberReq, GroupMemberReqBean.class);
        map.put(groupMemberRes, GroupMemberResBean.class);

        //发送响应的实体（发送消息、发送响应、接受消息）
        map.put(groupSendMsgReq, GroupSendMsgReqBean.class);
        map.put(groupSendMsgRes, GroupSendMsgResBean.class);
        map.put(groupRecMsg, GroupRecMsgBean.class);
    }

    //4. 根据指令获取对应的实体


    public static Class<? extends BaseBean> getBean(Byte code) {
        try {
            return map.get(code);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
