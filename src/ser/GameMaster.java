import java.awt.*;
import java.awt.event.*;

import javax.management.openmbean.SimpleType;

public class GameMaster extends Canvas implements KeyListener{

    Image buf;
    Graphics buf_gc;
    Dimension d;
    private int imgW, imgH;

    private int mode = 0;
    private int i, j;

    private int len = 39; //最小単位40
    int tmp;

    Character chara = new Character(imgW, imgH);    
    Image charaImg = this.getToolkit().getImage("character.png");

    MapA map = new MapA();

    Image mapImgData = this.getToolkit().getImage("mapImage.png");
    Image mapImg;

    boolean isServer = true;
    Server ser;

    int select = 0;
    boolean space = false;
    boolean enter = false;
    int count = 0;
    int[] walkPtn = {0,1,2,1};

    int[][][] charaNum = {
        {{16,232},{16,168},{16,41},{16,104}},
        {{112,232},{112,168},{112,41},{112,104}},
        {{208,232},{208,168},{208,41},{208,104}},
        {{304,232},{304,168},{304,41},{304,104}},
        {{16,488},{16,424},{16,296},{16,104}},
        {{112,488},{112,424},{112,296},{112,360}},
        {{208,488},{208,424},{208,296},{208,360}},
        {{304,488},{304,424},{304,296},{304,360}},
    };

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
        count+=1;
        count %= 4;
        //backPaint();
        
        switch (mode) {
        case 0:
            //スタート画面
            //select 0 => single, 1=> multi

            buf_gc.setColor(Color.red);

            if (select%2 == 0){
                buf_gc.drawLine(150, 360, 180, 360);
            }else{
                buf_gc.drawLine(550, 360, 580, 360);
            }

            buf_gc.setColor(Color.black);
            buf_gc.drawString("single", 150, 360);
            buf_gc.drawString("multi", 550, 360);

            if(space == true){
                if (select == 0){
                    mode = 1;
                }else{
                    mode = 1;
                }
            }

            space = false;
            break;
        case 9:
            //スタート画面
            buf_gc.setColor(Color.black);
            buf_gc.drawString("wait", 360, 360);

            ser = new Server();
            ser.run();
            ser.close();
            
            //mode = 1;
            break;
        case 2:
            len = 39;
            backPaint();
            buf_gc.setColor(Color.black);
            buf_gc.drawRect(0, 0, 100, 200);

            buf_gc.setColor(Color.red);
            buf_gc.drawRect(0, 0, 719, 719);

            if(!map.wallColissionCheck(chara)){
                chara.move(buf_gc, imgW, imgH);
            //printAroundWall();
            }else{
                chara.turn();
            }
            paintChara(chara.num, chara.vec, chara.x, chara.y);
            //buf_gc.drawImage(charaImg, chara.x, chara.y, len+chara.x, len+chara.y, chara.xImage-chara.wImage, chara.yImage-chara.hImage, chara.xImage+chara.wImage, chara.yImage+chara.hImage, null);
            break;
        case 1:
            //キャラクター選択画面
            tmp = select%8;
            len = 99;

            for (i = 0; i<2; i++){
                for (j=0; j<4; j++){
                    paintChara(i*4+j, 3, 150*j+20, 150+150*i);
                    if (tmp == i*4+j){
                        buf_gc.setColor(Color.red);
                        buf_gc.drawLine(150*j+20, 150*(i+1)+99,150*j+119, 150*(i+1)+99);
                    }
                }
            }

            
            if (enter==true){
                chara.num = tmp;
                mode =2;
                enter = false;
            }
        }
        g.drawImage(buf, 0, 0, this);
    }

    public void paintChara(int num, int vec, int x, int y){
        buf_gc.drawImage(charaImg, x, y, len+x, len+y, charaNum[num][vec-1][0]-13+32*walkPtn[count], charaNum[num][vec-1][1]-23, charaNum[num][vec-1][0]+13+32*walkPtn[count], charaNum[num][vec-1][1]+23, null);
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
    
    public void keyTyped(KeyEvent ke) {
        int cd = ke.getKeyCode();
        switch (cd) {
        case KeyEvent.VK_SPACE:
            break;            
        }
    }

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
            select-=1;
            break;
        case KeyEvent.VK_DOWN:
            chara.dflag = false;
            select+=1;
            break;
        case KeyEvent.VK_SPACE:
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

        case KeyEvent.VK_SPACE:
            space = true;
            break;
        
        case KeyEvent.VK_ENTER:
            enter = true;
            break;
        }
        
    }
}
