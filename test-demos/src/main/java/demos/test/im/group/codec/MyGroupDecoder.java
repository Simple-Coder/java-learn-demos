package demos.test.im.group.codec;

import com.alibaba.fastjson.JSON;
import demos.test.im.BaseBean;
import demos.test.im.single.bean.MapUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * Created by xiedong
 * Date: 2023/5/21 15:09
 */
public class MyGroupDecoder extends ByteToMessageDecoder {


    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {

        //1.根据协议取出数据
        int tag = byteBuf.readInt();//标识符
        byte code = byteBuf.readByte();//获取指令
        int len = byteBuf.readInt();//获取数据长度
        byte[] bytes = new byte[len];
        byteBuf.readBytes(bytes);

        //2.根据code获取类型
        Class<? extends BaseBean> c = MapUtils.getBean(code);

        //3.反序列化
        BaseBean baseBean = JSON.parseObject(bytes, c);

        list.add(baseBean);
    }
}
