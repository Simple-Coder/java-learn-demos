package demos.test.io.demo1;

import lombok.SneakyThrows;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by xiedong
 * Date: 2023/5/18 22:01
 */
public class BIOServer1 {
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
            String content = new String(buffer);
            System.out.println("接收到的数据:" + content);
        }
    }
}
