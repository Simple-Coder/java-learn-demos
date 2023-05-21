package demos.test.im;

import demos.test.im.group.bean.*;
import demos.test.im.single.bean.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xiedong
 * Date: 2023/5/21 15:11
 */
public class MapUtils {
//1. 自定义指令

    public static Byte codeLoginReq = 1;

    public static Byte codeLoginRes = 2;

    public static Byte codeMsgReq = 3;

    public static Byte codeMsgRes = 4;

    public static Byte codeMsgRec = 5;

    public static Byte groupCreateReq = 6;

    public static Byte groupCreateRes = 7;
    public static Byte groupListReq = 8;
    public static Byte groupListRes = 9;
    public static Byte groupAddReq = 10;
    public static Byte groupAddRes = 11;
    public static Byte groupQuitReq = 12;
    public static Byte groupQuitRes = 13;
    public static Byte groupMemberReq = 14;
    public static Byte groupMemberRes = 15;
    public static Byte groupSendMsgReq = 16;
    public static Byte groupSendMsgRes = 17;
    public static Byte groupRecMsg = 18;

    //2. 自定义一个Map，专门管理指令和实体的关系
    public static Map<Byte, Class<? extends BaseBean>> map = new HashMap<Byte, Class<? extends BaseBean>>();

    //3. 初始化
    static {
        map.put(codeLoginReq, LoginReqBean.class);
        map.put(codeLoginRes, LoginResBean.class);
        map.put(codeMsgReq, MsgReqBean.class);
        map.put(codeMsgRes, MsgResBean.class);
        map.put(codeMsgRec, MsgRecBean.class);


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

//    public static Map<Byte, Class<? extends BaseBean>> map = new HashMap<Byte, Class<? extends BaseBean>>();

//    static {
//        //登录的请求和响应实体
//        map.put(1, LoginReqBean.class);
//        map.put(2, LoginResBean.class);
//
//        //创建群组的请求和响应实体
//        map.put(3, GroupCreateReqBean.class);
//        map.put(4, GroupCreateResBean.class);
//
//        //查看群组的请求和响应实体
//        map.put(5, GroupListReqBean.class);
//        map.put(6, GroupListResBean.class);
//
//        //加入群组的请求和响应实体
//        map.put(7, GroupAddReqBean.class);
//        map.put(8, GroupAddResBean.class);
//
//        //退出群组的请求和响应实体
//        map.put(9, GroupQuitReqBean.class);
//        map.put(10, GroupQuitResBean.class);
//
//        //查看成员列表的请求和响应实体
//        map.put(11, GroupMemberReqBean.class);
//        map.put(12, GroupMemberResBean.class);
//
//        //发送响应的实体（发送消息、发送响应、接受消息）
//        map.put(13, GroupSendMsgReqBean.class);
//        map.put(14, GroupSendMsgResBean.class);
//        map.put(15, GroupRecMsgBean.class);
//    }
}
