package demos.test.im.single.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xiedong
 * Date: 2023/5/21 15:11
 */
public class MapUtils {
//1. 自定义指令

    private static Byte codeLoginReq = 1;

    private static Byte codeLoginRes = 2;

    private static Byte codeMsgReq = 3;

    private static Byte codeMsgRes = 4;

    private static Byte codeMsgRec = 5;

    //2. 自定义一个Map，专门管理指令和实体的关系
    private static Map<Byte, Class<? extends BaseBean>> map = new HashMap<Byte, Class<? extends BaseBean>>();

    //3. 初始化
    static {
        map.put(codeLoginReq, LoginReqBean.class);
        map.put(codeLoginRes, LoginResBean.class);
        map.put(codeMsgReq, MsgReqBean.class);
        map.put(codeMsgRes, MsgResBean.class);
        map.put(codeMsgRec, MsgRecBean.class);
    }

    //4. 根据指令获取对应的实体


    public static Class<? extends BaseBean> getBean(Byte code) {
        try {
            return map.get(code);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

//    private static Map<Byte, Class<? extends BaseBean>> map = new HashMap<Byte, Class<? extends BaseBean>>();

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
