package demos.test.io.heartbeat.client.handler;

import demos.test.io.heartbeat.client.TcpClient;
import demos.test.io.heartbeat.client.retry.RetryPolicy;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.EventLoop;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * Created by xiedong
 * Date: 2023/5/25 22:16
 */
@ChannelHandler.Sharable
@Slf4j
public class ReconnectHandler extends ChannelInboundHandlerAdapter {

    private int retries = 0;
    private RetryPolicy retryPolicy;

    private TcpClient tcpClient;

    public ReconnectHandler(TcpClient tcpClient) {
        this.tcpClient = tcpClient;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("【Client】与服务端建立连接成功!");
        retries = 0;
        ctx.fireChannelActive();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        if (retries == 0) {
            log.error("【Client】 丢失TCP连接");
//            System.err.println("Lost the TCP connection with the server.");
            ctx.close();
        }

        boolean allowRetry = getRetryPolicy().allowRetry(retries);
        if (allowRetry) {

            long sleepTimeMs = getRetryPolicy().getSleepTimeMs(retries);
            log.info("【Client】将在【{}】ms后尝试重连服务器，已重试次数：【{}】", sleepTimeMs, ++retries);
//            System.out.println(String.format("Try to reconnect to the server after %dms. Retry count: %d.", sleepTimeMs, ++retries));

            final EventLoop eventLoop = ctx.channel().eventLoop();
            eventLoop.schedule(() -> {
                log.info("【Client】重连服务器中....");
//                System.out.println("Reconnecting ...");
                tcpClient.connect();
            }, sleepTimeMs, TimeUnit.MILLISECONDS);
        }
        ctx.fireChannelInactive();
    }


    private RetryPolicy getRetryPolicy() {
        if (this.retryPolicy == null) {
            this.retryPolicy = tcpClient.getRetryPolicy();
        }
        return this.retryPolicy;
    }
}
