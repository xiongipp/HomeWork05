package TR;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    private Socket socket;
    private InputStreamReader reader;
    private BufferedReader bufferedReader;
    private PrintWriter writer;
    String response="";

    public String getResponse() {
        return response;
    }
    public void Login(String name,String command) throws IOException {
            getNameAndCommand(name,command);
    }

    public void getNameAndCommand(String name,String command) throws IOException {
        Link();//连接服务端
        String str = name;
        sendMessage(str+","+command);//发给服务器用户名字,和需要的指令
        String res =getMessage(); //接受服务端回复
        this.response=res;//暂时存放回复信息
        System.out.println("来自服务器" + res);
        release();
    }

    public void sendMessage(String mes) throws IOException {
        writer.println(mes);
        writer.flush();
    }
    public  String getMessage() throws IOException {
        return bufferedReader.readLine();
    }
    public void Link() throws IOException {
        socket = new Socket("127.0.0.1", 888);
        reader = new InputStreamReader(socket.getInputStream());
        bufferedReader = new BufferedReader(reader);
        writer = new PrintWriter(socket.getOutputStream());

    }
    public void release() throws IOException {
        writer.close();
        bufferedReader.close();
        socket.close();
    }
}



