
import java.awt.Graphics;

public class Player {

    private int centerX = 15;
    private int centerY = 15;
    private int speedX = 0;
    private int speedY = 0;



    public void update() {
        if (speedX < 0) {
            centerX += speedX; //This changes centerX by adding speedX.
        }
        else if (speedX > 0) {
            centerX += speedX; //This changes centerX by adding speedX.
        }

        if (speedY < 0) {
            centerY += speedY; //This changes centerY by adding speedY.
        }
        else if (speedY > 0) {
            centerY += speedY; //This changes centerY by adding speedY.
        }
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
