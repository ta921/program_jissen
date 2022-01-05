import java.awt.*;
import java.awt.event.*;

public class GameMaster extends Canvas implements KeyListener{

    Image buf;
    Graphics buf_gc;
    Dimension d;
    private int imgW, imgH;

    private int mode = 0 ;
    private int i, j;

    private int len = 40; //最小単位

    Character chara = new Character(imgW, imgH);    
    Image charaImg = this.getToolkit().getImage("character.png");

    MapA map = new MapA();

    Image mapImgData = this.getToolkit().getImage("mapImage.png");
    Image mapImg;

    GameMaster(int imgW, int imgH){
        this.imgW = imgW;
        this.imgH = imgH;
        setSize(imgW, imgH);

        addKeyListener(this);

        //map.repaint();
    }

    public void backPaint(){
        for(i=0; i<24; i++){
            for(j=0; j<32; j++){
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
        backPaint();
        
        switch (mode) {
        case 0:
            buf_gc.setColor(Color.black);
            buf_gc.drawRect(0, 0, 100, 200);


            buf_gc.drawImage(charaImg, 0+chara.x, 0+chara.y, len+chara.x, len+chara.y, chara.xImage-chara.wImage, chara.yImage-chara.hImage, chara.xImage+chara.wImage, chara.yImage+chara.hImage, null);
            buf_gc.drawRect(0, 0, 100, 200);

            if (!map.wallColissionCheck(chara)){
                chara.move(buf_gc, imgW, imgH);
            }
            break;
        }
        g.drawImage(buf, 0, 0, this);
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
            chara.lflag = true;
            System.out.println(" [a] pressed");
            break;

        case KeyEvent.VK_RIGHT:
            chara.rflag = true;
            System.out.println(" [d] pressed");
            break;

        case KeyEvent.VK_UP:
            chara.uflag = true;
            System.out.println(" [w] pressed");
            break;

        case KeyEvent.VK_DOWN:
            chara.dflag = true;
            System.out.println(" [s] pressed");
            break;
        }
    }
}
