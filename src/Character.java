import java.awt.*;

class Character extends MovingObject{
    boolean lflag;
    boolean rflag;
    boolean uflag;
    boolean dflag;

    int i,j;

    Character (int apWidth, int apHeight) {
        x = (int)(apWidth/2);
        y = (int)(apHeight/2);

        v = 5;

        w = 5;
        h = 5;

        xImage = 16;
        yImage = 41;

        wImage = 13;
        hImage = 23;

        lflag = false;
        rflag = false;
        uflag = false;
        dflag = false;
    }

    void revive (int apWidth, int apHeight) {

    }

    void move (Graphics buf, int apWidth, int apHeight) {
        if (xImage > 80){
            xImage = 16;
        }
        xImage+=32;

        if (lflag && !rflag){
            x = x - v;
            vec = 4;
            yImage = 104;
        }

        if (rflag && !lflag){
            x = x + v;
            vec = 3;
            yImage = 168;
        }

        if (uflag && !dflag){
            y = y - v;
            vec = 1;
            yImage = 232;
        }

        if (dflag && !uflag){
            y = y + v;
            vec = 2;
            yImage = 41;
        }
    }
}
