package talkroom;

import javax.swing.*;
import java.awt.*;

public class GUI {
    public static void main(String[] args) {
        GUI gui=new GUI();
        gui.initFrame();
        gui.showLogin();
    }
    //public Client cli = null;
    public int width = 720;
    public int height = 550;
    public JFrame jFrame = null; //界面窗口
    public JLayeredPane layeredPane = null; //层叠容器
    public JPanel backLayer = null; //背景层
    public JPanel frontLayer = null; //前景层
    public CardLayout cardLayout = null; //前景层布局器
    public void initCli(){

    }
    public void  initFrame(){
        JFrame jFrame=new JFrame("登录程序");
        layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(width, height));
        jFrame.add(layeredPane);
        jFrame.setResizable(false);
        jFrame.pack();
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        backLayer = new JPanel();
        ((FlowLayout)backLayer.getLayout()).setHgap(0);
        ((FlowLayout)backLayer.getLayout()).setVgap(0);
        backLayer.setSize(width,height);
        backLayer.setLocation(0,0);
        JLabel bg = new JLabel(new ImageIcon("12.jpg"));
        backLayer.add(bg);
        layeredPane.add(backLayer,new Integer(0));
        frontLayer = new JPanel();
        cardLayout = new CardLayout(0,0);
        frontLayer.setLayout(cardLayout);
        frontLayer.setOpaque(false);
        frontLayer.setSize(width,height);
        frontLayer.setLocation(0,0);
        layeredPane.add(frontLayer,new Integer(1));
    }
    JPanel loginPane=null;
    JTextField LoginNameInput=null;
    JTextField RegisterNameInput=null;
    public  void  showLogin(){
        if(loginPane ==null){
            loginPane=new JPanel();
            loginPane.setOpaque(false);
            JPanel code_panel=new JPanel();
            LoginNameInput=new JTextField(10);
            code_panel.add(LoginNameInput);
        }

    }
}

