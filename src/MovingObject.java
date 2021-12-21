import java.awt.Graphics;

abstract class MovingObject {
    int xBottomLeft;
    int yBottomLeft;
    int xTopRight = xBottomLeft + 10;
    int yTopRight = yBottomLeft + 10;
    int hp;
    int v;
    int vec = 1;

    void move(Graphics buf, int apWidth, int apHeight) {
    }
}
