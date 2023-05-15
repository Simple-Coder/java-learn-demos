package demos.test.netty.demo;

import lombok.SneakyThrows;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * Created by xiedong
 * Date: 2023/5/15 21:42
 */
public class Server {
    @SneakyThrows
    public static void main(String[] args) {
        byte[] buffer = new byte[1024];
        ServerSocket serverSocket = new ServerSocket(8080);
        System.out.println("服务器已启动并监听8080端口");
        while (true) {
            System.out.println();
            System.out.println("服务器正在等待连接...");
            Socket socket = serverSocket.accept();

            System.out.println("服务器已接收到连接请求...");
            System.out.println();

            System.out.println("服务器正在等待数据...");
            socket.getInputStream().read(buffer);

            System.out.println("服务器已经接收到数据");
            System.out.println();

            String content = new String(buffer, StandardCharsets.UTF_8);
            System.out.println("接收到的数据:" + content);
        }
    }
}
