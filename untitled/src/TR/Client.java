package TR;

import java.io.*;
import java.net.Socket;

public class Client {
    private Socket socket;
    private InputStreamReader reader;
    private BufferedReader bufferedReader;
    private PrintWriter writer;
    String response="";

    public Client() throws IOException {
    }
    public String getResponse() {
        return response;
    }
    public void Login(String name,String command) throws IOException {
            getNameAndCommand(name,command);
    }
    public void getNameAndCommand(String name,String command) throws IOException {
        String str = name;
        socket = new Socket("127.0.0.1", 888);
        new Send(socket).send(str+","+command);//发给服务器用户名字,和需要的指令
        String res= new Receive(socket).receive();//接受服务端回复
        this.response=res;//暂时存放回复信息
        System.out.println("来自服务器" + res);
        socket.close();
    }
    public Socket createSocket() throws IOException {
     return socket = new Socket("127.0.0.1", 888);
    }
    public void createOutputStream(Socket s) throws IOException {
        writer=new PrintWriter(s.getOutputStream());
    }
    public void createInputStream(Socket s) throws IOException {
        reader = new InputStreamReader(s.getInputStream());
        bufferedReader=new BufferedReader(reader);
    }
    public static class Send{
        private Socket socket;
        private PrintWriter writer;
        public Send(Socket client) throws IOException {
            this.socket=client;
            writer=new PrintWriter(socket.getOutputStream());
        }
        public void send(String msg){
            writer.println(msg);
            writer.flush();
        }
    }
    public static class Receive{
        private Socket socket;
        private InputStreamReader reader;
        private BufferedReader bufferedReader;
        public Receive(Socket client) throws IOException {
            this.socket=client;
            reader = new InputStreamReader(socket.getInputStream());
            bufferedReader=new BufferedReader(reader);
        }
        public String receive() throws IOException {
            return bufferedReader.readLine();
        }
    }
    public void sendMessage(String mes) throws IOException {
        writer.println(mes);
        writer.flush();
    }
    public  String getMessage() throws IOException {
        return bufferedReader.readLine();
    }
    public void release() throws IOException {
        writer.close();
        bufferedReader.close();
        socket.close();
    }
}



