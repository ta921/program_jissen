import java.awt.Graphics;

abstract class MovingObject {
    int x;
    int y;

    int w;
    int h;

    int hp;
    int v;
    int vec = 3;

    int xImage;
    int yImage;

    int wImage;
    int hImage;

    MovingObject () {}

    MovingObject (int width, int height) {
        x = (int)(Math.random()*width);
        y = (int)(Math.random()*height);

        w = 2;
        h = 2;
        hp = 1;
    }

    abstract void revive(int w, int h);

    abstract void move(Graphics buf, int apwidth, int apheight);

    boolean collisionCheck(MovingObject obj) {
        if (Math.abs(this.x - obj.x) <= (this.w+obj.w) && Math.abs(this.y-obj.y) <= (this.h+obj.h)) {
            this.hp--;
            obj.hp--;

            return true;
        }else{
            return false;
        }
    }
}
