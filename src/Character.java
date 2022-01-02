import java.awt.*;

class Character extends MovingObject{
    boolean lflag;
    boolean rflag;
    boolean uflag;
    boolean dflag;

    Character (int apWidth, int apHeight) {
        x = (int)(apWidth/2);
        y = (int)(apHeight/2);

        v = 1;

        w = 5;
        h = 5;

        xImage = 10;
        yImage = 30;

        wImage = 10;
        hImage = 30;

        lflag = false;
        rflag = false;
        uflag = false;
        dflag = false;
    }

    void revive (int apWidth, int apHeight) {

    }

    void move (Graphics buf, int apWidth, int apHeight) {
        System.out.println("move");
        if (xImage > 145){
            xImage = 0;
        }
        xImage+=40;

        if (lflag && !rflag && x > w){
            x = x - v;
            vec = 4;
            yImage = 145;
        }

        if (rflag && !lflag && x > apWidth - w){
            x = x + v;
            vec = 3;
            yImage = 235;
        }

        if (uflag && !dflag && y > h){
            y = y - v;
            vec = 1;
            yImage = 325;
        }

        if (dflag && !uflag && x > apHeight - y){
            y = y + v;
            vec = 2;
            yImage = 55;
        }
    }
}
