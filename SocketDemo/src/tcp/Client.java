package tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 18080)) {
            // 向服务器发送消息
            OutputStream os = socket.getOutputStream();
            PrintWriter pw = new PrintWriter(os);
            pw.write("new message");
            pw.flush(); // 将缓冲区的数据放到输出流中
            socket.shutdownOutput();

            // 接收从服务器发来的消息
            InputStream is = socket.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String info;
            while ((info = br.readLine()) != null) {
                System.out.println("message: " + info);
            }
            br.close();
            is.close();
            pw.close();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
