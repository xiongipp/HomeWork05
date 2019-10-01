package TR;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Server {
    static List<String> nameList=new LinkedList<>();
    public  static CopyOnWriteArrayList<SocketHandler> socketHandlers=new CopyOnWriteArrayList<>();//容器装为客户端服务的handler
    public static void main(String[] args) throws IOException {
        ServerSocket server=new ServerSocket(888);
        while (true){
            Socket socket=server.accept();
            SocketHandler handler=new SocketHandler(socket);
            socketHandlers.add(handler);//每开启一个线程就将它加入到容器
            Thread thread=new Thread(handler);
            thread.start();
        }
    }

 static  class SocketHandler implements Runnable{
       private InputStreamReader reader;
       private BufferedReader bufferedReader;
       private PrintWriter writer;
       private Socket socket;

        //创建对象时就初始化输入输出流
        public SocketHandler(Socket socket) throws IOException {
            this.socket = socket;
            reader = new InputStreamReader(socket.getInputStream());
            bufferedReader = new BufferedReader(reader);
            writer = new PrintWriter(socket.getOutputStream());
        }
        //封装发送接收方法
        public String getMessage() throws IOException {
            return bufferedReader.readLine();
        }
        public  void sendMessage(String msg){
            writer.println(msg);
            writer.flush();
        }
        public void sendAllMessage(String msg){
            for(SocketHandler socketHandler: socketHandlers){
                if(socketHandler.equals(this)){
                  socketHandler.sendMessage(msg);
                  continue;
                }
                socketHandler.sendMessage(msg);
            }
        }
        //释放资源的方法
        public void release() {
            writer.close();
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        @Override
        public void run() {
            try {
                while (true){
                    String str = getMessage();
                    if(!str.equals("")){
                        if(str.contains(",")){
                            String[] nac = str.split(",");//把命令信息用‘，’分割开，获取到名字还有指令
                            String uName = nac[0];
                            String command = nac[1];
                            //如果收到了注册命令
                            if(command.equals("register")){
                                //校验用是否已经存在
                                if(nameList.contains(uName)){
                                    sendMessage("该用户名已经存在");
                                }else{
                                    //再检查一遍用户名是否为空？
                                    if (str.length() != 0) {
                                        nameList.add(uName);
                                        sendMessage("OK");//发给客户端说允许注册
                                    } else {
                                        sendMessage("error");//出现错误返回信息
                                    }
                                }
                                //收到登录命令
                            }else if(command.equals("login")) {
                                if(nameList.contains(uName)) {
                                    sendMessage("用户存在允许登录");
                                }else {
                                    sendMessage("用户不存在，请检查用户名后重试");
                                }
                                writer.flush();
                                writer.close();
                                bufferedReader.close();
                                socket.close();
                            }
                        }
                        else
                            sendMessage(str);
                    }
                }
        } catch (IOException e) {
                e.printStackTrace();
                release();
            }
        }
    }
}




