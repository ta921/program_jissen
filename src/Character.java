import java.awt.*;
import java.util.Random;

class Character extends MovingObject{
    boolean lflag;
    boolean rflag;
    boolean uflag;
    boolean dflag;

    int i,j;

    Random r = new Random();
    int num;

    Character (int apWidth, int apHeight) {
        x = 40;
        y = 40;

        v = 5;

        w = 39;
        h = 39;

        hp = 1;

        xImage = 16;
        yImage = 41;

        wImage = 13;
        hImage = 23;

        lflag = false;
        rflag = false;
        uflag = false;
        dflag = false;
        num = r.nextInt(8);
    }

    void revive (int apWidth, int apHeight) {}

    void set (){
        x = r.nextInt(18)*40;
        y = r.nextInt(18)*40;
    }

    void move (Graphics buf, int apWidth, int apHeight) {
        if (hp==0){
            return;
        }

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

    void collisionCheck(Character enemy) {
        switch(enemy.vec){
        case 1:
            if(enemy.y <= this.y+40 && this.y <= enemy.y && enemy.x-39 <= this.x && this.x<= enemy.x+39) {
                if(this.vec == 3){
                    enemy.vec = 3;
                    this.vec = 1;
                }else{
                    this.hp=0;
                }
            }
            break;
        case 2:
            if(enemy.x+40 >= this.x && this.x >= enemy.x && enemy.y-39 <= this.y && this.y<= enemy.y+39) {
                if(this.vec == 4){
                    enemy.vec = 2;
                    this.vec = 4;
                }else{
                    this.hp=0;
                }
            }
            break;
        case 3:
            if(enemy.y+40 >= this.y && this.y >= enemy.y && enemy.x-39 <= this.x && this.x<= enemy.x+39) {
                if(this.vec == 1){
                    enemy.vec = 1;
                    this.vec = 3;
                }else{
                    this.hp=0;
                }
            }
            break;
        case 4:
            if(enemy.x <= this.x+40 && this.x <= enemy.x && enemy.y-39 <= this.y && this.y<= enemy.y+39) {
                if(this.vec == 2){
                    enemy.vec = 2;
                    this.vec = 4;
                }else{
                    this.hp=0;
                }
            }
            break;
        }
    }
}