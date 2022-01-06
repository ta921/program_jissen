import java.awt.*;
import java.util.Random;

class Character extends MovingObject{
    boolean lflag;
    boolean rflag;
    boolean uflag;
    boolean dflag;

    int i,j;

    Random r = new Random();

    Character (int apWidth, int apHeight) {
        x = 40;
        y = 40;

        v = 5;

        w = 39;
        h = 39;

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

        if (vec == 4){
            x = x - v;
            yImage = 104;
        }

        if (vec == 2){
            x = x + v;
            yImage = 168;
        }

        if (vec == 1){
            y = y - v;
            yImage = 232;
        }

        if (vec == 3){
            y = y + v;
            yImage = 41;
        }
    }

    void turn(){
        this.vec = r.nextInt(4)+1;
    }
}
