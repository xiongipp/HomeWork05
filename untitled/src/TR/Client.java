package TR;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    String cliName = "";
    String response="";

    public String getCliName() {
        return cliName;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public void setCliName(String cliName) {
        this.cliName = cliName;
    }

    public void getNameAndCommand(String name,String command) throws IOException {

        Socket socket = new Socket("127.0.0.1", 888);
        InputStreamReader reader = new InputStreamReader(socket.getInputStream());
        BufferedReader bufferedReader = new BufferedReader(reader);
        PrintWriter writer = new PrintWriter(socket.getOutputStream());
        String str = name;
        writer.println(str+","+command);//发给服务器用户名字,和需要的指令
        writer.flush();
        String res = bufferedReader.readLine();//接受服务端回复
        this.response=res;//暂时存放回复信息
        System.out.println("来自服务器" + res);
        writer.close();
        bufferedReader.close();
        socket.close();
    }
}



