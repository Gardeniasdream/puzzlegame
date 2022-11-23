package ui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.ContentHandlerFactory;
import java.util.Random;

public class GameJFrame extends JFrame implements KeyListener, ActionListener {
    //创建一个二维数组保存打乱的图片索引
    int[][] data = new int[4][4];

    //记录空白图片的位置
    int x = 0;
    int y = 0;

    //定义一个变量，记录当前展示图片的路径
    String path = "puzzlegame\\image\\animal\\animal3\\";

    //定义一个二维数组，存储正确的数据
    int[][] win = {
            {1,2,3,4},
            {5,6,7,8},
            {9,10,11,12},
            {13,14,15,0},
    };

    //定义变量，用来统计步数
    int step = 0;

    //创建选项下面的条目对象
    JMenuItem replayItem = new JMenuItem("重新游戏");
    JMenuItem reLoginItem = new JMenuItem("重新登录");
    JMenuItem closeItem = new JMenuItem("关闭游戏");
    JMenuItem changeItem1 = new JMenuItem("动物");
    JMenuItem changeItem2 = new JMenuItem("运动");
    JMenuItem accountItem = new JMenuItem("略略略");

    //和游戏运行的代码都在这个界面
    public GameJFrame() {
        //初始化界面
        initJFrame();

        //初始化菜单
        initJMenuBar();

        //初始化数据（打乱）
        initData();

        //初始化图片
        initImage();

        //将界面显示出来
        this.setVisible(true);
    }

    //打乱数据
    private void initData() {
        //1、定义一个一维数组
        int[] tempArr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        //2、打乱数组中的数据的顺序
        Random r = new Random();
        for (int i = 0; i < tempArr.length; i++) {
            //获取随机索引
            int index = r.nextInt(tempArr.length);
            //拿着遍历的每一个数据，跟随机索引上的数据进行交换
            int temp = tempArr[i];
            tempArr[i] = tempArr[index];
            tempArr[index] = temp;
        }
        //3、给二维数组添加数据
        for (int i = 0; i < tempArr.length; i++) {
            if (tempArr[i] == 0) {
                x = i / 4;
                y = i % 4;
            }
            data[i / 4][i % 4] = tempArr[i];
        }
    }

    //初始化图片
    private void initImage() {
        //清空原本已经出现的所有图片
        this.getContentPane().removeAll();

        if (victory()) {
            //显示胜利图标
            JLabel winJLabel = new JLabel(new ImageIcon("puzzlegame\\image\\win.png"));
            winJLabel.setBounds(203,283,197,73);
            this.getContentPane().add(winJLabel);
        }

        JLabel stepCount = new JLabel("步数："+step);
        stepCount.setBounds(50,30,100,20);
        this.getContentPane().add(stepCount);

        //先加载的图片在上方
        //外循环  把内循环重复执行4次
        for (int i = 0; i < 4; i++) {
            //内循环  表示在一行添加4张图片
            for (int j = 0; j < 4; j++) {
                //创建一个JLabel的对象（管理容器）
                int num = data[i][j];
                JLabel jLabel = new JLabel(new ImageIcon(path + num + ".jpg"));
                //指定图片位置
                jLabel.setBounds(105 * j + 83, 105 * i + 134, 105, 105);
                //给图片添加边框
                //0:图片凸起 1：图片凹下
                jLabel.setBorder(new BevelBorder(1));
                //把管理容器添加到界面中
                this.getContentPane().add(jLabel);
            }
        }

        //添加背景图片
        JLabel background = new JLabel(new ImageIcon("puzzlegame\\image\\background.png"));
        //指定图片位置
        background.setBounds(40, 40, 508, 560);
        //把管理容器添加到界面中
        this.getContentPane().add(background);

        //刷新页面
        this.getContentPane().repaint();

    }

    //初始化菜单
    private void initJMenuBar() {
        //创建整个菜单对象
        JMenuBar jMenuBar = new JMenuBar();
        //创建菜单上面的两个选项的对象（功能 关于我们）
        JMenu functionJMenu = new JMenu("功能");
        JMenu aboutJMenu = new JMenu("关于我们");
        JMenu changeJMenu = new JMenu("更换图片");

        //将每一个选项下面的条目添加到选项中
        functionJMenu.add(changeJMenu);
        functionJMenu.add(replayItem);
        functionJMenu.add(reLoginItem);
        functionJMenu.add(closeItem);

        changeJMenu.add(changeItem1);
        changeJMenu.add(changeItem2);

        aboutJMenu.add(accountItem);

        //给条目绑定事件
        replayItem.addActionListener(this);
        reLoginItem.addActionListener(this);
        closeItem.addActionListener(this);
        changeItem1.addActionListener(this);
        changeItem2.addActionListener(this);
        accountItem.addActionListener(this);

        //将菜单里面的选项添加到菜单中
        jMenuBar.add(functionJMenu);
        jMenuBar.add(aboutJMenu);

        //给整个界面设置菜单
        this.setJMenuBar(jMenuBar);
    }

    //初始化界面
    private void initJFrame() {
        //在创建界面的时候设置宽高
        this.setSize(603, 680);
        //设置界面的标题
        this.setTitle("拼图单机版 v1.0");
        //设置界面置顶
        this.setAlwaysOnTop(true);
        //设置界面居中
        this.setLocationRelativeTo(null);
        //设置游戏关闭模式
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //取消默认的居中位置
        this.setLayout(null);
        //给整个界面添加键盘监听事件
        this.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == 65){
            //把界面中所有图片全部删除
            this.getContentPane().removeAll();
            //加载第一张完整图片
            JLabel all = new JLabel(new ImageIcon(path + "all.jpg"));
            all.setBounds(83, 134, 420, 420);
            this.getContentPane().add(all);

            //加载背景图片
            JLabel background = new JLabel(new ImageIcon("puzzlegame\\image\\background.png"));
            //指定图片位置
            background.setBounds(40, 40, 508, 560);
            //把管理容器添加到界面中
            this.getContentPane().add(background);
            //刷新界面
            this.getContentPane().repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e)  {
        //判断游戏是否胜利，如果胜利，此方法需要直接结束，不能再执行下面的移动代码了
        if (victory()) {
            //结束方法
            return;
        }
        //对上、下、左、右进行判断
        //左：37 上：38 右：39 下：40
        int code = e.getKeyCode();
        if(code == 37){
            //已经在边界上了
            if(y==3){
                return;
            }
            //把空白右面的方块左移，把空白方块右面的数字赋值给空白方块
            //x不变，y+1
            data[x][y]=data[x][y+1];
            data[x][y+1]=0;
            //记录空白方块变化
            y++;
            //每移动一次计数器增加一次
            step++;
            initImage();
            System.out.println("左");
        }else if(code == 38){
            //已经在边界上了
            if(x==3){
                return;
            }
            //把空白下面的方块上移，把空白方块下面的数字赋值给空白方块
            //x+1，y不变
            data[x][y]=data[x+1][y];
            data[x+1][y]=0;
            //记录空白方块变化
            x++;
            //每移动一次计数器增加一次
            step++;
            initImage();
            System.out.println("上");
        }else if(code == 39){
            //已经在边界上了
            if(y==0){
                return;
            }
            //把空白左面的方块右移，把空白方块左面的数字赋值给空白方块
            //x不变，y-1
            data[x][y]=data[x][y-1];
            data[x][y-1]=0;
            //记录空白方块变化
            y--;
            //每移动一次计数器增加一次
            step++;
            initImage();
            System.out.println("右");
        }else if (code==40){
            //已经在边界上了
            if(x==0){
                return;
            }
            //把空白下面的方块下移，把空白方块上面的数字赋值给空白方块
            //x-1，y不变
            data[x][y]=data[x-1][y];
            data[x-1][y]=0;
            //记录空白方块变化
            x--;
            //每移动一次计数器增加一次
            step++;
            initImage();
            System.out.println("下");
        }else if(code == 65){
            initImage();
        }else if(code == 87){
            data = new int[][]{
                    {1,2,3,4},
                    {5,6,7,8},
                    {9,10,11,12},
                    {13,14,15,0}
            };
            initImage();
        }
    }

    //判断data数组中的数据是否跟win数组中的相同
    //如果全部相同，返回true，否则false
    public boolean victory(){
        for (int i = 0; i < data.length; i++) {
            //i:表示二维数组data里面的索引
            //data[i]:一次表示每一个一维数组
            for (int j = 0; j < data[i].length; j++) {
                if(data[i][j] != win[i][j])
                    //只要有一个数据不一样，则返回false
                    return false;
            }
        }
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //给切换图片准备随机数
        Random r = new Random();
        //获取当前被点击的条目对象
        Object obj = e.getSource();
        //判断
        if(obj == replayItem){
            System.out.println("重新开始");
            initData();//打乱数组
            step = 0;//计数器清零
            initImage();//重新加载图片
        }else if(obj == reLoginItem){
            System.out.println("重新登录");
            //返回登录界面
            this.setVisible(false);
            //打开登录界面
            new LoginJFrame();
        }else if(obj == closeItem){
            System.out.println("关闭游戏");
            //直接关闭虚拟机
            System.exit(0);
        }else if(obj == accountItem){
            System.out.println("略略略");
            //创建弹框对象
            JDialog jDialog = new JDialog();
            //创建一个管理图片的容器对象JLabel
            JLabel jLabel = new JLabel(new ImageIcon("puzzlegame\\image\\about.jpg"));
            jLabel.setBounds(0,0,444,444);
            jDialog.getContentPane().add(jLabel);
            //设置弹框大小
            jDialog.setSize(500,500);
            //让弹框置顶
            jDialog.setAlwaysOnTop(true);
            //让弹框居中
            jDialog.setLocationRelativeTo(null);
            //弹框不关闭无法操作下面的界面
            jDialog.setModal(true);
            //显示弹框
            jDialog.setVisible(true);
        }else if(obj == changeItem1){
            path = "puzzlegame\\image\\animal\\animal"+(r.nextInt(7)+1)+"\\";
            initData();//打乱数组
            step = 0;//计数器清零
            initImage();//重新加载图片
        }else if(obj == changeItem2){
            path = "puzzlegame\\image\\sport\\sport"+(r.nextInt(9)+1)+"\\";
            initData();//打乱数组
            step = 0;//计数器清零
            initImage();//重新加载图片
        }
    }
}
