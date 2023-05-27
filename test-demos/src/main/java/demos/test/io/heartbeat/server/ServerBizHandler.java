package demos.test.io.heartbeat.server;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by xiedong
 * Date: 2023/5/25 22:11
 * 收到来自客户端的数据包后, 直接在控制台打印出来.
 */
@ChannelHandler.Sharable
@Slf4j
public class ServerBizHandler extends SimpleChannelInboundHandler<String> {

    private final String REC_HEART_BEAT = "I had received the heart beat!";

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String data) throws Exception {
        try {
            log.info("【Server】接收到数据：【{}】", data);
//            System.out.println("receive data: " + data);
//            ctx.writeAndFlush(REC_HEART_BEAT);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("【Server】与客户端建立连接成功");
//        System.out.println("Established connection with the remote client.");

        // do something

        ctx.fireChannelActive();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.info("【Server】与客户端断开连接了...");
//        System.out.println("Disconnected with the remote client.");

        // do something

        ctx.fireChannelInactive();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
