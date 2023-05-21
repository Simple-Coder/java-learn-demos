package demos.test.im.group;

import demos.test.im.group.codec.MyGroupDecoder;
import demos.test.im.group.codec.MyGroupEncoder;
import demos.test.im.group.handler.ClientChatGroupHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * Created by xiedong
 * Date: 2023/5/21 15:19
 */
public class GroupChatClient {
    public static void main(String[] args) throws Exception {
        String host = args[0];
        int port = Integer.parseInt(args[1]);
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            Bootstrap b = new Bootstrap(); // (1)
            b.group(workerGroup); // (2)
            b.channel(NioSocketChannel.class); // (3)
            b.option(ChannelOption.SO_KEEPALIVE, true); // (4)
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    //1.拆包器
                    ch.pipeline().addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 5, 4));
                    //2.自定义解码器
                    ch.pipeline().addLast(new MyGroupDecoder());
                    //3.自定义业务
//                    ch.pipeline().addLast(new ClientChatHandler());
                    ch.pipeline().addLast(new ClientChatGroupHandler());
                    //4.自定义编码器
                    ch.pipeline().addLast(new MyGroupEncoder());
                }
            });

            // Start the client.
            ChannelFuture f = b.connect(host, port).sync(); // (5)

            // Wait until the connection is closed.
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
        }
    }
}
