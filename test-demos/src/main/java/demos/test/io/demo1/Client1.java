package demos.test.io.demo1;

import lombok.SneakyThrows;

import java.net.Socket;

/**
 * Created by xiedong
 * Date: 2023/5/18 22:01
 */
public class Client1 {
    @SneakyThrows
    public static void main(String[] args) {
        Socket socket = new Socket("127.0.0.1", 8080);
        socket.getOutputStream().write("向服务器发数据".getBytes());
        socket.close();
    }
}
