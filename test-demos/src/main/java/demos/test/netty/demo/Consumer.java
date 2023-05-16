package demos.test.netty.demo;

import lombok.SneakyThrows;

import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * Created by xiedong
 * Date: 2023/5/15 21:43
 */
public class Consumer {
    @SneakyThrows
    public static void main(String[] args) {
        Socket socket = new Socket("127.0.0.1", 8080);
        socket.getOutputStream().write("客户端正在发送".getBytes(StandardCharsets.UTF_8));
        socket.close();
    }
}
