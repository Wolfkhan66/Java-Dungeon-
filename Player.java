
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player {

    private int centerX = 400;
    private int centerY = 240;
    private int speedX = 0;
    private int speedY = 0;

    public static Rectangle Top = new Rectangle(0, 0, 0, 0);
    public static Rectangle Bottom = new Rectangle(0, 0, 0, 0);
    public static Rectangle Left = new Rectangle(0, 0, 0, 0);
    public static Rectangle Right = new Rectangle(0, 0, 0, 0);
    public static Rectangle CollisionZone = new Rectangle(0, 0, 0, 0);

    public void update() {

        Top.setRect(centerX - 25 , centerY - 30, 50, 10);
        Bottom.setRect(centerX - 25, centerY + 20, 50, 10);
        Left.setRect(centerX - 30, centerY - 25, 10, 50);
        Right.setRect(centerX + 20 , centerY -25, 10, 50);
        CollisionZone.setRect(centerX - 90, centerY - 90, 180, 180);
    }

    public void moveRight() {
        speedX = -6;
    }

    public void moveLeft() {
        speedX = 6;
    }

    public void moveUp() {
        speedY = 6;
    }

    public void moveDown() {
        speedY =  -6;
    }

    public void stop() {
        speedX = 0;
        speedY = 0;
    }

    public int getCenterX() {
        return centerX;
    }

    public int getCenterY() {
        return centerY;
    }

    public int getSpeedX() {
        return speedX;
    }

    public int getSpeedY() {
        return speedY;
    }

    public void setCenterX(int centerX) {
        this.centerX = centerX;
    }

    public void setCenterY(int centerY) {
        this.centerY = centerY;
    }

    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }

    public void setSpeedY(int speedY) {
        this.speedY = speedY;
    }

}
