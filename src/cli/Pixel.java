import java.awt.*;

public class Pixel extends Frame implements Runnable {

    Thread th;
    GameMaster gm;
    boolean isServer;

    public static void main(String[] args) {
        new Pixel();
    }

    Pixel() {
        super("Pixel");
        int cW=720, cH=720; //キャンパスのサイズ
        this.setSize(cW, cH); //フレームのサイズを指定
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10)); //キャンパスをフレームに設置

        gm = new GameMaster(cW, cH);

        this.add(gm);
        this.setVisible(true); //可視化

        th = new Thread(this);
        th.start();

        requestFocusInWindow();
    }

    public void run() {
        try{
            while (true) {
                Thread.sleep(50);
                gm.repaint();
            }
        }
    catch (Exception e) {/*System.out.println("Exception: " +e);*/}
    }
    
}
