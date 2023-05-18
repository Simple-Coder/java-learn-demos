package demos.test.io.bio;

import lombok.SneakyThrows;

import java.net.Socket;
import java.util.Scanner;

/**
 * Created by xiedong
 * Date: 2023/5/18 22:07
 */
public class Client2 {
    @SneakyThrows
    public static void main(String[] args) {
        Socket socket = new Socket("127.0.0.1", 8080);
        String message = null;
        Scanner sc = new Scanner(System.in);
        message = sc.next();
        socket.getOutputStream().write(message.getBytes());
        socket.close();
        sc.close();
    }
}
