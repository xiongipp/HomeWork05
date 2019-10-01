package TR;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Send  implements Runnable {
    private Socket socket;
    private InputStreamReader reader;
    private BufferedReader bufferedReader;
    private PrintWriter writer;
    private  ChatForm chatForm;
    public Send(Socket client) throws IOException {
        this.socket=client;
        writer=new PrintWriter(socket.getOutputStream());
    }
    @Override
    public void run() {
        while (true){
        String message=chatForm.getTextField_send().getText();
        if(message!=null){
            send(message);
        }
        }
    }
    public void send(String msg) {
        writer.println(msg);
        writer.flush();
    }
    public  void getChatForm(ChatForm chatForm){
        this.chatForm=chatForm;
    }
}
