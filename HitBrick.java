package Project;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

class BallThread extends Thread{
    private HitBrick m;
    BallThread(HitBrick a){
        //super();
        m=a;
    }
    public void run(){
        m.move();
        m.repaint();
    }
}//小球的运动轨迹
class Brick{
    Rectangle rect=null;//创建一个空白界面
    //长方形对象，砖块按钮的位置和宽高
    int brick_x,brick_y;
    //按扭的左上角坐标
    int brick_width,brick_height;
    //按扭的宽和高
    Boolean visible;//*
    public Brick(int x,int y,int w,int h)
       {
        brick_x=x;
        brick_y=y;
        brick_width=w;
        brick_height=h;
        visible=true;
        rect=new Rectangle(x,y,w,h);
        //创建长方形对象---砖块按钮的位置和宽高。
    }
}
class Ball{
    Rectangle rect=null;
    int ball_x,ball_y;
    int ball_width,ball_height;
    public Ball(int x,int y,int w,int h){
        ball_x=x;
        ball_y=y;
        ball_width=w;
        ball_height=h;
        rect=new Rectangle(x,y,w,h);
    }
}

public class HitBrick extends JFrame implements KeyListener{
    private BallThread greenBallThread;
    //控制小球的线程
    private Boolean xUp,yUp,bouncing;
    private int x,y,xDx,yDy;
    //小球坐标,增量
    private final int MAX_X=600,MAX_Y=500;
    private Boolean renew;
    private JLabel label;
    private int Rx,Ry;
    //横板坐标
    private Brick brick[]=new Brick[36];
    //砖块
    private Ball ball;
    //小球
    public HitBrick(){
        super("打砖块");//*
        Container pane=getContentPane();
        //设置空白面板容器
        label=new JLabel("按空格键开始");
        //标签
        label.setHorizontalAlignment(JLabel.CENTER);
        //水平
        label.setVerticalAlignment(JLabel.BOTTOM);
        //垂直
        pane.add(label);
        //向面板里添加标签
        xUp=true;
        //横坐标可以移动
        yUp=false;
        //纵坐标不可以移动
        xDx=2;
        yDy=2;
        x=300;
        //小球坐标
        y=450;
        Rx=265;
        //横板坐标
        Ry=460;
        renew=true;
        bouncing=false;//*
        addKeyListener(this);
        //键盘监听器
        brick[0]=new Brick(0,60,50,20);
        //砖块坐标
        brick[1]=new Brick(50,60,50,20);
        brick[2]=new Brick(100,60,50,20);
        brick[3]=new Brick(150,60,50,20);
        brick[4]=new Brick(200,60,50,20);
        brick[5]=new Brick(250,60,50,20);
        brick[6]=new Brick(0,90,50,20);
        brick[7]=new Brick(50,110,50,20);
        brick[8]=new Brick(100,130,50,20);
        brick[9]=new Brick(150,130,50,20);
        brick[10]=new Brick(200,110,50,20);
        brick[11]=new Brick(250,90,50,20);
        brick[12]=new Brick(0,160,50,20);
        brick[13]=new Brick(50,160,50,20);
        brick[14]=new Brick(100,160,50,20);
        brick[15]=new Brick(150,160,50,20);
        brick[16]=new Brick(200,160,50,20);
        brick[17]=new Brick(250,160,50,20);
        brick[18]=new Brick(300,60,50,20);
        brick[19]=new Brick(350,60,50,20);
        brick[20]=new Brick(400,60,50,20);
        brick[21]=new Brick(450,60,50,20);
        brick[22]=new Brick(500,60,50,20);
        brick[23]=new Brick(550,60,50,20);
        brick[24]=new Brick(300,90,50,20);
        brick[25]=new Brick(350,110,50,20);
        brick[26]=new Brick(400,130,50,20);
        brick[27]=new Brick(450,130,50,20);
        brick[28]=new Brick(500,110,50,20);
        brick[29]=new Brick(550,90,50,20);
        brick[30]=new Brick(300,160,50,20);
        brick[31]=new Brick(350,160,50,20);
        brick[32]=new Brick(400,160,50,20);
        brick[33]=new Brick(450,160,50,20);
        brick[34]=new Brick(500,160,50,20);
        brick[35]=new Brick(550,160,50,20);
        ball=new Ball(300,450,10,10);
        //球的坐标
        setSize(MAX_X,MAX_Y);
        //窗口大小
        setResizable(false);//窗口的大小不可调
        setVisible( true );//让程序变成可视化
        //可视化
    }
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() ==e.VK_SPACE) {
            if(renew){
                greenBallThread=new BallThread(this);
                bouncing = true;//可以打掉砖块
                greenBallThread.start();
                label.setVisible(false);//使标签变得不可见
            }
            renew=false;//按空格不可重新开始
        }
        if(e.getKeyCode()==e.VK_LEFT){
            Rx=Rx-20;
            if(bouncing){
                if(Rx<0){
                    Rx=0;
                }
            } else{
                if(Rx<0){
                    Rx=0;
                } else{
                    x=x-20;
                    ball.ball_x=x;
                }
            }
            repaint();
        }
        if(e.getKeyCode()==e.VK_RIGHT){
            Rx=Rx+20;
            if(bouncing){
                if(Rx+80>600){
                    Rx=520;
                }
            } else{
                if(Rx+80>600){
                    Rx=520;
                } else{
                    x=x+20;
                    ball.ball_x=x;
                }
            }
            repaint();
        }
    }
   
    public void keyReleased (KeyEvent e) {
    }
    public void keyTyped (KeyEvent e){
    }
    public void paint(Graphics g){
        super.paint(g);
        ball.rect.setLocation(x,y);
        if(bouncing){
            for (int i=0;i<=35;i++){
                if(brick[i].visible==true){
                    switch(i){
                        case 0 :g.setColor(Color.blue);
                        break;
                        case 1 :g.setColor(Color.cyan);
                        break;
                        case 2 :g.setColor(Color.gray);
                        break;
                        case 3 :g.setColor(Color.green);
                        break;
                        case 4 :g.setColor(Color.magenta);
                        break;
                        case 5 :g.setColor(Color.yellow);
                        break;
                        case 6 :g.setColor(Color.white);
                        break;
                        case 7 :g.setColor(Color.black);
                        break;
                        case 8 :g.setColor(Color.orange);
                        break;
                        case 9 :g.setColor(Color.pink);
                        break;
                        case 10 :g.setColor(Color.darkGray);
                        break;
                        case 11 :g.setColor(Color.red);
                        break;
                        case 12 :g.setColor(Color.blue);
                        break;
                        case 13 :g.setColor(Color.cyan);
                        break;
                        case 14 :g.setColor(Color.gray);
                        break;
                        case 15 :g.setColor(Color.green);
                        break;
                        case 16 :g.setColor(Color.magenta);
                        break;
                        case 17 :g.setColor(Color.yellow);
                        break;
                        case 18 :g.setColor(Color.blue);
                        break;
                        case 19 :g.setColor(Color.cyan);
                        break;
                        case 20 :g.setColor(Color.gray);
                        break;
                        case 21 :g.setColor(Color.green);
                        break;
                        case 22 :g.setColor(Color.magenta);
                        break;
                        case 23 :g.setColor(Color.yellow);
                        break;
                        case 24 :g.setColor(Color.white);
                        break;
                        case 25 :g.setColor(Color.black);
                        break;
                        case 26 :g.setColor(Color.orange);
                        break;
                        case 27 :g.setColor(Color.pink);
                        break;
                        case 28 :g.setColor(Color.darkGray);
                        break;
                        case 29 :g.setColor(Color.red);
                        break;
                        case 30 :g.setColor(Color.blue);
                        break;
                        case 31 :g.setColor(Color.cyan);
                        break;
                        case 32 :g.setColor(Color.gray);
                        break;
                        case 33 :g.setColor(Color.green);
                        break;
                        case 34 :g.setColor(Color.magenta);
                        break;
                        case 35 :g.setColor(Color.yellow);
                        break;
                    }
                    g.fill3DRect(brick[i].brick_x,brick[i].brick_y,brick[i].brick_width,brick[i].brick_height,true);//利用3d画出砖块
                }
            }
            g.setColor(Color.red);
            g.fillOval(x, y, 10, 10);
            g.setColor(Color.blue);
            g.fillRect(Rx,Ry,80,20);
        } else{
            for (int i=0;i<=35;i++){
                switch(i){
                    case 0 :g.setColor(Color.blue);
                    break;
                    case 1 :g.setColor(Color.cyan);
                    break;
                    case 2 :g.setColor(Color.gray);
                    break;
                    case 3 :g.setColor(Color.green);
                    break;
                    case 4 :g.setColor(Color.magenta);
                    break;
                    case 5 :g.setColor(Color.yellow);
                    break;
                    case 6 :g.setColor(Color.white);
                    break;
                    case 7 :g.setColor(Color.black);
                    break;
                    case 8 :g.setColor(Color.orange);
                    break;
                    case 9 :g.setColor(Color.pink);
                    break;
                    case 10 :g.setColor(Color.darkGray);
                    break;
                    case 11 :g.setColor(Color.red);
                    break;
                    case 12 :g.setColor(Color.blue);
                    break;
                    case 13 :g.setColor(Color.cyan);
                    break;
                    case 14 :g.setColor(Color.gray);
                    break;
                    case 15 :g.setColor(Color.green);
                    break;
                    case 16 :g.setColor(Color.magenta);
                    break;
                    case 17 :g.setColor(Color.yellow);
                    break;
                    case 18 :g.setColor(Color.blue);
                    break;
                    case 19 :g.setColor(Color.cyan);
                    break;
                    case 20 :g.setColor(Color.gray);
                    break;
                    case 21 :g.setColor(Color.green);
                    break;
                    case 22 :g.setColor(Color.magenta);
                    break;
                    case 23 :g.setColor(Color.yellow);
                    break;
                    case 24 :g.setColor(Color.white);
                    break;
                    case 25 :g.setColor(Color.black);
                    break;
                    case 26 :g.setColor(Color.orange);
                    break;
                    case 27 :g.setColor(Color.pink);
                    break;
                    case 28 :g.setColor(Color.darkGray);
                    break;
                    case 29 :g.setColor(Color.red);
                    break;
                    case 30 :g.setColor(Color.blue);
                    break;
                    case 31 :g.setColor(Color.cyan);
                    break;
                    case 32 :g.setColor(Color.gray);
                    break;
                    case 33 :g.setColor(Color.green);
                    break;
                    case 34 :g.setColor(Color.magenta);
                    break;
                    case 35 :g.setColor(Color.yellow);
                    break;
                }
                g.fill3DRect(brick[i].brick_x,brick[i].brick_y,brick[i].brick_width,brick[i].brick_height,true);
            }
            g.setColor(Color.red);
            g.fillOval(x, y, 10, 10);
            g.setColor(Color.blue);
            g.fillRect(Rx, Ry, 80, 20);
        }
    }
    public void move(){
        while(true){
            try{
                Thread.currentThread().sleep(25);//定义小球速度
            }
            catch(InterruptedException exception){
                System.err.println(exception.toString());//在屏幕上打印
            }//打印小球的动作
            for (int i=0;i<=35;i++){
                if(ball.rect.intersects(brick[i].rect)&&brick[i].visible){//如果球碰到可见的砖块时
                    brick[i].visible=false;//砖块不可见
                    yUp=!yUp;
                    //打到球不可见
                }
            }
            if(x+5>Rx&&x+5<Rx+80&&y+10>=Ry){//小球在横板的坐标的范围内
                yUp=false;
                xDx=(int)(Math.random()*5+2);
                //小球坐标增量
                yDy=(int)(Math.random()*5+2);
            }
            if(xUp==true){
                x+=xDx;
                //小球左右移动坐标改变
            } else{
                x-=xDx;
            }
            if(yUp==true){
                y+=yDy;
            } else{
                y-=yDy;
            }
            if(y<=0){
                y=0;
                ball.ball_y=y;
                yUp=true;
                xDx=(int)(2+Math.random()*5);
                yDy=(int)(2+Math.random()*5);
            } else if(y>=MAX_Y-15){
                //yDy=(int)(2+Math.random()*5);
                yUp=false;
                break;
            }
            if(x<=0){
                x=0;
                ball.ball_x=x;
                xUp=true;
                xDx=(int)(2+Math.random()*5);
                yDy=(int)(2+Math.random()*5);
            } else if(x>=MAX_X-10){
                x=MAX_X-10;
                ball.ball_x=x;
                xDx=(int)(2+Math.random()*5);
                yDy=(int)(2+Math.random()*5);
                xUp=false;
            }//在两墙反弹
            ball.rect.setLocation(ball.ball_x,ball.ball_y);//球的实时位置
            repaint();//刷新
            int i;
            //如果所有砖块都不可见
            for (i=0;i<=35&&brick[i].visible==false;i++){//所有的球都不可见的时候
                //则重新玩
            }
            if(i==36){
                break;
            }
            //
        }
        renew=true;
        //初始化
        bouncing=false;
        for (int i=0;i<=35;i++){
            brick[i].visible=true;
        }
        xUp=true;
        yUp=false;
        xDx=2;
        yDy=2;
        x=300;
        y=450;
        Rx=265;
        Ry=460;
        //
//        repaint();
//        repaint();
        label.setVisible(true);
    }
    public static void main(String[] args) {
        HitBrick mar=new HitBrick();
    }
  }
