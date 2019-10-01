package TR;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Receive implements Runnable{
    private Socket socket;
    private InputStreamReader reader;
    private BufferedReader bufferedReader;
    private PrintWriter writer;
    @Override
    public void run() {
        while (true){
            try {
                String msg=receive();
                System.out.println(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public Receive(Socket client) throws IOException {
        this.socket=client;
        reader = new InputStreamReader(socket.getInputStream());
        bufferedReader=new BufferedReader(reader);
    }
    public String receive() throws IOException {
        return bufferedReader.readLine();
    }
}
