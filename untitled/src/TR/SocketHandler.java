package TR;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.*;

public class SocketHandler implements Runnable{
    private Socket socket;
    static List<String> nameList=new LinkedList<>();
    public SocketHandler(Socket socket) {
        this.socket = socket;
    }
    @Override
    public void run() {
        try {
            InputStreamReader reader = new InputStreamReader(socket.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(reader);
            PrintWriter writer = new PrintWriter(socket.getOutputStream());

            String str = bufferedReader.readLine();
            System.out.println("来自客户端" + str);
            if(str.contains(",")){
            String[] nac = str.split(",");//把信息用‘，’分割开，获取到名字还有指令
            String uName = nac[0];
            String command = nac[1];
           //如果收到了注册命令
            if(command.equals("register")){
                //校验用是否已经存在
                if(nameList.contains(uName)){
                    writer.println("该用户名已经存在");
                }else{
                    //再检查一遍用户名是否为空？
                    if (str.length() != 0) {
                        nameList.add(uName);
                        writer.println("OK");//发给客户端说允许注册
                    } else {
                        writer.println("error");//出现错误返回信息
                    }
                }
                //收到登录命令
            }else if(command.equals("login")) {
                if(nameList.contains(uName)) {
                    writer.println("用户存在允许登录");
                }else {
                writer.println("用户不存在，请检查用户名后重试");
                }
                writer.flush();
                writer.close();
                bufferedReader.close();
                socket.close();
            }
            }else {
                writer.println(str);
            }
                writer.flush();
                writer.close();
                bufferedReader.close();
                socket.close();
            } catch(IOException e){
                e.printStackTrace();
            }

    }

}
