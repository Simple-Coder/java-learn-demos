package demos.test.io.heartbeat.client;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import io.netty.util.concurrent.ScheduledFuture;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by xiedong
 * Date: 2023/5/25 22:09
 * 客户端连接到服务器端后，会循环执行一个任务：随机等待几秒，然后ping一下Server端，即发送一个心跳包。
 */
@Slf4j
public class Pinger extends ChannelInboundHandlerAdapter {

    private Random random = new Random();
    private int baseRandom = 8;

    private Channel channel;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        this.channel = ctx.channel();

        ping(ctx.channel());
    }

    private void ping(Channel channel) {
        int second = Math.max(1, random.nextInt(baseRandom));
        if (second == 5) {
            second = 6;
        }
//        System.out.println("next heart beat will send after " + second + "s.");
        log.info("【Client】下一次心跳包将会在【{}】s后发送", second);
        ScheduledFuture<?> future = channel.eventLoop().schedule(() -> {
            if (channel.isActive()) {
//                System.out.println("sending heart beat to the server...");
                log.info("【Client】正在向服务端发送心跳包");
                channel.writeAndFlush(ClientIdleStateTrigger.HEART_BEAT);
            } else {
                log.info("【Client】连接已断开，取消定时发送心跳包");
//                System.err.println("The connection had broken, cancel the task that will send a heart beat.");
                channel.closeFuture();
                throw new RuntimeException();
            }
        }, second, TimeUnit.SECONDS);

        future.addListener(new GenericFutureListener() {
            @Override
            public void operationComplete(Future future) throws Exception {
                if (future.isSuccess()) {
                    ping(channel);
                }
            }
        });
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // 当Channel已经断开的情况下, 仍然发送数据, 会抛异常, 该方法会被调用.
        cause.printStackTrace();
        ctx.close();
    }
}
