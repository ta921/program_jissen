import java.awt.*;
import java.awt.event.*;

public class GameMaster extends Canvas implements KeyListener{

    Image buf;
    Graphics buf_gc;
    Dimension d;
    private int imgW, imgH;

    private int mode = 0 ;
    private int i, j;

    private int len = 39; //最小単位40

    Character chara = new Character(imgW, imgH);    
    Image charaImg = this.getToolkit().getImage("character.png");

    MapA map = new MapA();

    Image mapImgData = this.getToolkit().getImage("mapImage.png");
    Image mapImg;

    boolean isServer = true;
    Server ser;
    Client cli;

    GameMaster(int imgW, int imgH){
        this.imgW = imgW;
        this.imgH = imgH;
        setSize(imgW, imgH);

        addKeyListener(this);

        //map.repaint();
    }

    public void backPaint(){
        for(i=0; i<18; i++){
            for(j=0; j<18; j++){
                if (map.map[i][j] == 1){
                    buf_gc.drawImage(mapImgData, 40*j, 40*i, 40*j+40, 40*i+40, map.x-map.w, map.y-map.h, map.x+map.w, map.y+map.h, null);
                }

            }
        }
    }

    public void addNotify(){
        super.addNotify();
        buf = createImage(imgW, imgH);
        buf_gc = buf.getGraphics();
    }

    public void paint(Graphics g){
        buf_gc.setColor(Color.white);
        buf_gc.fillRect(0, 0, imgW, imgH);
        //backPaint();
        
        switch (mode) {
        case 0:
            //スタート画面
            buf_gc.setColor(Color.black);
            buf_gc.drawString("wait", 360, 360);

            cli = new Client();
            cli.run();
            cli.close();
            
            //mode = 1;
            break;
        case 1:
            buf_gc.setColor(Color.black);
            buf_gc.drawRect(0, 0, 100, 200);


            //buf_gc.drawImage(charaImg, chara.x, chara.y, len+chara.x, len+chara.y, chara.xImage-chara.wImage, chara.yImage-chara.hImage, chara.xImage+chara.wImage, chara.yImage+chara.hImage, null);
            buf_gc.setColor(Color.red);
            buf_gc.drawRect(0, 0, 719, 719);

            if(!map.wallColissionCheck(chara)){
                chara.move(buf_gc, imgW, imgH);
            //printAroundWall();
            }else{
                chara.turn();
            }
            buf_gc.drawImage(charaImg, chara.x, chara.y, len+chara.x, len+chara.y, chara.xImage-chara.wImage, chara.yImage-chara.hImage, chara.xImage+chara.wImage, chara.yImage+chara.hImage, null);
            break;
        }
        g.drawImage(buf, 0, 0, this);
    }

    public void printAroundWall(){
        System.out.print(" ");
        System.out.print(map.map[chara.y/40-1][chara.x/40]);
        System.out.println(" ");
        System.out.print(map.map[chara.y/40][chara.x/40-1]);
        System.out.print(map.map[chara.y/40][chara.x/40]);
        System.out.println(map.map[chara.y/40][chara.x/40+1]);
        System.out.print(" ");
        System.out.print(map.map[chara.y/40+1][chara.x/40]);
        System.out.println(" ");
        System.out.println("///////////////////////////////");
    }

    public void update(Graphics gc) {
        paint(gc);
    }
    
    public void keyTyped(KeyEvent ke) {}

    public void keyReleased(KeyEvent ke) {
        int cd = ke.getKeyCode();
        switch (cd) {               
        case KeyEvent.VK_RIGHT:
            chara.rflag = false;
            break;
        case KeyEvent.VK_LEFT:
            chara.lflag = false;
            break;
        case KeyEvent.VK_UP:
            chara.uflag = false;
            break;
        case KeyEvent.VK_DOWN:
            chara.dflag = false;
            break;
        }
    }

    public void keyPressed(KeyEvent ke) {
        int cd = ke.getKeyCode();
        switch (cd) {
        case KeyEvent.VK_LEFT:
            chara.vec = 4;
            break;

        case KeyEvent.VK_RIGHT:
            chara.vec = 2;
            break;

        case KeyEvent.VK_UP:
            chara.vec = 1;
            break;

        case KeyEvent.VK_DOWN:
            chara.vec = 3;
            break;
        }
    }
}
