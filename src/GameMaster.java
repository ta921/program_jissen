import java.awt.*;
import java.awt.event.*;

public class GameMaster extends Canvas implements KeyListener{

    Image buf;
    Graphics buf_gc;
    Dimension d;
    private int imgW, imgH;

    private int mode = 0 ;
    private int i, j;

    Character chara = new Character(imgW, imgH);
    Image charaImg = this.getToolkit().getImage("character.png");

    GameMaster(int imgW, int imgH){
        this.imgW = imgW;
        this.imgH = imgH;
        setSize(imgW, imgH);

        addKeyListener(this);
    }

    public void addNotify(){
        super.addNotify();
        buf = createImage(imgW, imgH);
        buf_gc = buf.getGraphics();
    }

    public void paint(Graphics g){
        buf_gc.setColor(Color.white);
        buf_gc.fillRect(0, 0, imgW, imgH);
        switch (mode) {
        case 0:
            buf_gc.drawImage(charaImg, 0, 0, 100, 200, chara.xImage-chara.wImage, chara.yImage-chara.hImage, chara.xImage+chara.wImage, chara.yImage+chara.hImage, this);
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
            break;

        case KeyEvent.VK_RIGHT:
            chara.rflag = true;
            break;

        case KeyEvent.VK_UP:
            chara.uflag = true;
            break;

        case KeyEvent.VK_DOWN:
            chara.dflag = true;
            break;
        }
    }
}
