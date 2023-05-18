package demos.test.io.demo5.chat.handler;

/**
 * Created by xiedong
 * Date: 2023/5/18 22:23
 */

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.Set;

/**
 * Handles a server-side channel.
 */
public class ChatHandler extends ChannelInboundHandlerAdapter { // (1)

    Set<Channel> channelList = new HashSet<>();

    @Override

    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //通知其他人，我上线了
        channelList.forEach(e -> {
            e.writeAndFlush("[客户端]" + ctx.channel().remoteAddress() + "上线了");
        });
        channelList.add(ctx.channel());

    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf out = (ByteBuf) msg;
        String message = out.toString(Charset.defaultCharset());

        //通知其他人，我上线了
        channelList.forEach(e -> {
            if (e == ctx.channel()) {
                e.writeAndFlush("[自己] ： " + message);
            } else {
                e.writeAndFlush("[客户端] ： " + ctx.channel().remoteAddress() + "" + " ： " + message);
            }
        });

    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        channelList.remove(ctx.channel());
        //通知其他人，我下线了
        channelList.forEach(e -> {
            e.writeAndFlush("[客户端]" + ctx.channel().remoteAddress() + "上线了");
        });
    }
}
