package TR;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class GUI2 {
    private JPanel jp;
    private JTextField textField1;
    private JButton register_button;
    private JTextArea textArea1;
    private JButton login_Button;
    private String name;

    public GUI2() {
        //注册按钮添加事件监听
        register_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                name=textField1.getText();
                if(name.equals("")){
                    textArea1.setText("你的注册名字不能为空");
                }else{
                    Client client=new Client();
                    System.out.println(name);
                    try {
                        client.getNameAndCommand(name,"register");
                        if(client.getResponse().equals("OK")){
                            textArea1.setText("允许注册！注册成功");
                        }else if(client.getResponse().equals("error")){
                            textArea1.setText("注册失败");
                        }else if(client.getResponse().equals("该用户名已经存在")){
                            textArea1.setText("该用户名已经存在");
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
        //登录按钮添加事件监听
        login_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                name=textField1.getText();
                if(name.equals("")){
                    textArea1.setText("用户名不能为空!");
                }else {
                    Client client=new Client();
                    System.out.println(name);
                    try {
                        client.getNameAndCommand(name,"login");
                        client.getResponse();
                        if(client.getResponse().equals("用户存在允许登录")){
                            textArea1.setText("登录啦！");
                        }else {
                            textArea1.setText(client.getResponse());
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
    }

    public static void main(String[] args) {
       GUI2 gui2=new GUI2();
       gui2.initFrame();

    }
    public void  initFrame(){
        JFrame frame = new JFrame("TR.GUI2");
        frame.setContentPane(new GUI2().jp);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(400,400);
        frame.setLocation(300,300);
        frame.setVisible(true);

    }

    public String getName() {
        return name;
    }
    public  void  setName(String string){
        name=string;
    }


}
