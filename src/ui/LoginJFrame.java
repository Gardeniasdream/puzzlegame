package ui;

import user.User;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class LoginJFrame extends JFrame implements MouseListener {
    static ArrayList<User> list = new ArrayList<User>();
    static {
        list.add(new User("zhangsan","123"));
    }

    //登录图片
    JButton login = new JButton();

    String loginpath = "puzzlegame\\image\\login\\登录按钮.png";

    //用户名输入
    JTextField userText = new JTextField();
    //密码输入
    JPasswordField passwordText = new JPasswordField();

    //和登录相关的界面都在这里
    public LoginJFrame(){
        //初始化界面
        initJFrame();

        //添加图片
        initImage();

        //将界面显示出来
        this.setVisible(true);
    }

    public void initImage(){
        //用户名图片
        JLabel user = new JLabel(new ImageIcon("puzzlegame\\image\\login\\用户名.png"));
        //指定图片位置
        user.setBounds(100, 170, 47, 17);
        //把管理容器添加到界面中
        this.getContentPane().add(user);


        //指定用户名输入位置大小
        userText.setBounds(170, 170, 150, 20);
        //把管理容器添加到界面中
        this.getContentPane().add(userText);

        //密码图片
        JLabel password = new JLabel(new ImageIcon("puzzlegame\\image\\login\\密码.png"));
        //指定图片位置
        password.setBounds(100, 210, 32, 16);
        //把管理容器添加到界面中
        this.getContentPane().add(password);


        //密码输入位置大小
        passwordText.setBounds(170, 210, 150, 20);
        //把管理容器添加到界面中
        this.getContentPane().add(passwordText);


        //指定图片位置
        login.setBounds(160, 270, 128, 47);
        //指定登录图片
        login.setIcon(new ImageIcon(loginpath));
        //去掉边框
        login.setBorderPainted(false);
        //去掉背景
        login.setContentAreaFilled(false);
        //添加事件
        login.addMouseListener(this);
        //把管理容器添加到界面中
        this.getContentPane().add(login);


        JLabel jLabel = new JLabel(new ImageIcon("puzzlegame\\image\\login\\background.png"));
        //指定图片位置
        jLabel.setBounds(0, 0, 470, 390);
        //把管理容器添加到界面中
        this.getContentPane().add(jLabel);

        //刷新页面
        this.getContentPane().repaint();
    }

    //初始化界面
    public void initJFrame(){
        //在创建界面的时候设置宽高并显示
        this.setSize(480,420);
        //设置界面的标题
        this.setTitle("拼图 登录");
        //设置界面置顶
        this.setAlwaysOnTop(true);
        //设置界面居中
        this.setLocationRelativeTo(null);
        //设置游戏关闭模式
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //取消默认的居中位置
        this.setLayout(null);

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        Object obj = e.getSource();
        if(obj == login){
            //把界面中所有图片全部删除
            this.getContentPane().removeAll();
            System.out.println("naxia");
            //指定登录图片
            loginpath = "puzzlegame\\image\\login\\登录按下.png";
            initImage();
        }
        String userInput = userText.getText();
        char[] passwordInput = passwordText.getPassword();
        String passwordStr = String.valueOf(passwordInput);
        System.out.println(userInput);
        System.out.println(passwordInput);
        if(userInput.compareTo("zhangsan")==0 && passwordStr.compareTo("123")==0){
            //关闭当前界面
            this.setVisible(false);
            //打开游戏界面
            new GameJFrame();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Object obj = e.getSource();
        if(obj == login){
            //把界面中所有图片全部删除
            this.getContentPane().removeAll();
            System.out.println("松开");
            //指定登录图片
            loginpath = "puzzlegame\\image\\login\\登录按钮.png";
            initImage();
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
