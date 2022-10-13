package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @ClassName Dessert
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2022-09-09 15:59
 */
public class client {
    final static String ADDRESS = "127.0.0.1";
    final static int PORT = 7788;

    public static void main(String[] args) throws IOException {
        Socket socket = null;
        BufferedReader in = null;
        PrintWriter out = null;
        socket = new Socket(ADDRESS, PORT);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);

        //向服务器端发送数据
        out.println("接收到客户端的请求数据...");
        out.println("接收到客户端的请求数据1111...");
        String response = in.readLine();
        System.out.println("Client: " + response);
    }
}

