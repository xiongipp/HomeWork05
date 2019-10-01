package TR;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;

public class ChatForm {
    private JPanel JP1;
    private JTextField textField_send;

    public JTextField getTextField_send() {
        return textField_send;
    }

    public void setTextField_send(JTextField textField_send) {
        this.textField_send = textField_send;
    }

    public JTextField getTextField_get() {
        return textField_get;
    }

    public void setTextField_get(JTextField textField_get) {
        this.textField_get = textField_get;
    }

    private JTextField textField_get;
    private JButton send;
    private JLabel label1;
    private JLabel label2;
    private Client client=new Client();

    public ChatForm() throws IOException {
        send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message=textField_send.getText();
                try {
                    /*
                    * 自发自收*
                    * */
                    //发消息
                    Socket socket=client.createSocket();
                    client.createOutputStream(socket);
                    String msg=textField_send.getText();
                    client.sendMessage(msg);
                    //收消息
                    client.createInputStream(socket);
                    new Thread(new Receive(socket));
                    textField_get.setText(client.getMessage());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        textField_get.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
    public  void initChatFrame() throws IOException {
        JFrame frame = new JFrame("Chat");
        frame.setContentPane(new ChatForm().JP1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(600,400);
        frame.setLocation(300,300);
        frame.setVisible(true);
    }

    public void getCli(Client client) {
        this.client=client;
    }
}

