package udp;

import javax.crypto.spec.OAEPParameterSpec;
import java.io.IOError;
import java.io.IOException;
import java.net.CacheRequest;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.sql.SQLOutput;

/**
 * @ClassName Dessert
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2022-09-11 17:01
 */
public class Server {
    public static void main(String[] args) {
        byte[] bytes = new byte[1024];

        try {
            DatagramPacket packet = new DatagramPacket(bytes, bytes.length);
            DatagramSocket socket = new DatagramSocket(18080);

            // 接收客户端发送的数据，会阻塞
            socket.receive(packet);
            String msg = new String(packet.getData(), 0, packet.getLength());
            System.out.println(packet.getLength());
            System.out.println("massage: " + msg);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
