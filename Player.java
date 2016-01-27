
import java.awt.Graphics;

public class Player {

    private int centerX = 400;
    private int centerY = 240;
    private int speedX = 0;
    private int speedY = 0;



    public void update() {
    }

    public void moveRight() {
        speedX = 6;
    }

    public void moveLeft() {
        speedX = -6;
    }

    public void moveUp() {
        speedY = -6;
    }

    public void moveDown() {
        speedY =  6;
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
