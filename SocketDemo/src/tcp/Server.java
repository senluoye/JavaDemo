package tcp;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Exchanger;

public class Server {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(18080)) {
            Socket socket;
            while (true) {
                // 监听客户端连接
                socket = serverSocket.accept();
                ServerThread thread = new ServerThread(socket);
                thread.start();
                // 可以当作是ip地址
                InetAddress address = socket.getInetAddress();
                System.out.println("client ip: " + address.getHostAddress());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
