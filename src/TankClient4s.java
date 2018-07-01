import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TankClient4s extends Frame {

    int x=50, y=50;
    Image offScreenImage = null;//刷新率背后图片



    public static void main(String[] args){
        TankClient4s tc = new TankClient4s();
        tc.launchFrame();
    }

    public void paint(Graphics g){
        Color c = g.getColor();
        g.setColor(Color.red);
        g.fillOval(x,y,30,30);
        g.setColor(c);

        y+=5;
    }

    public void update(Graphics g){

        if(offScreenImage == null){
            offScreenImage = this.createImage(800,600);//picture
        }
        Graphics gOffScreen = offScreenImage.getGraphics();//背后图片的画笔
        Color c = gOffScreen.getColor();//擦除
        gOffScreen.setColor(Color.GREEN);//擦除
        gOffScreen.fillRect(0,0,800,600);//擦除
        gOffScreen.setColor(c);//擦除
        paint(gOffScreen);
        g.drawImage(offScreenImage,0,0,null);//java-desktop/awt/grapics/
    }

    public void launchFrame(){
        this.setLocation(300,300);
        this.setSize(800,600);
        this.setTitle("TankWar");
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        this.setBackground(Color.GREEN);
        this.setResizable(false);
        this.setVisible(true);
        new Thread(new PaintThread()).start();
    }

    private class PaintThread implements Runnable{
        public void run(){
            while(true){
                repaint();
                try{
                    Thread.sleep(50);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }


}
