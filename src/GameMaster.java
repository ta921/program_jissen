import java.awt.*;
import java.awt.event.*;

public class GameMaster extends Canvas implements KeyListener{

    Image buf;
    Graphics buf_gc;
    Dimension d;
    private int imgW, imgH;

    private int mode = 0 ;
    private int i, j;

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
        switch (mode) {

        }
    }

    public void update(Graphics gc) {
        paint(gc);
    }
    
    public void keyTyped(KeyEvent ke) {}

    public void keyReleased(KeyEvent ke) {}

    public void keyPressed(KeyEvent ke) {
    }
}
