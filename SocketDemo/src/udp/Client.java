package udp;

import com.sun.nio.sctp.SendFailedNotification;

import java.io.IOException;
import java.io.Serializable;
import java.net.*;
import java.nio.charset.StandardCharsets;

/**
 * @ClassName Dessert
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2022-09-11 16:54
 */
public class Client {
    public static void main(String[] args) {
        String msg = "message";

        try {
            // 获取服务端的ip地址
            InetAddress address = InetAddress.getByName("localhost");

            // 创建包对象并封装信息
            DatagramPacket packet = new DatagramPacket(msg.getBytes(), msg.getBytes().length, address, 18080);

            // 创建socket对象
            DatagramSocket socket = new DatagramSocket();

            // 发送
            socket.send(packet);
            socket.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
