import java.util.ArrayList;
import java.awt.Rectangle;
import java.awt.Image;
public class Enemy {

    public int maxHealth, currentHealth, power, speedX, speedY, centerX, centerY, type;
    public Image tileImage;
    public Rectangle r;
    public Player player = StartingClass.getplayer();
    // Behavioral Methods
    public void update() {

    }

    public void follow() {

        if (player.getCenterX()-25 >= centerX) {
            centerX += 1 ;
        } else {
            centerX -= 1;
        }
        if (player.getCenterY() - 25 >= centerY) {
            centerY += 1 ;
        } else {
            centerY -= 1;
        }
    }

    public void checkVerticalCollision(Rectangle rtop, Rectangle rbot){
        if (rtop.intersects(r)) {
            player.stopUp();
            player.setSpeedY(0);
            player.setCenterY(player.getCenterY() + 5);
            centerY -= 10;
            StartingClass.enemyarray.remove(this);
            StartingClass.score += 10;
            Player.health -= 5;
        }
        if (rbot.intersects(r)) {
            player.stopDown();
            player.setSpeedY(0);
            player.setCenterY(player.getCenterY() - 5);
            centerY += 10;
            StartingClass.enemyarray.remove(this);
            StartingClass.score += 10;
            Player.health -= 5;
        }
    }

    public void checkSideCollision(Rectangle rleft, Rectangle rright) {
        if (rleft.intersects(r)) {
            player.stopLeft();
            player.setSpeedX(0);
            player.setCenterX(player.getCenterX() + 5);
            centerX -= 10;
            StartingClass.enemyarray.remove(this);
            StartingClass.score += 10;
            Player.health -= 5;
        }
        if (rright.intersects(r)) {
            player.stopRight();
            player.setSpeedX(0);
            player.setCenterX(player.getCenterX() - 5);
            centerX += 10;
            StartingClass.enemyarray.remove(this);
            StartingClass.score += 10;
            Player.health -= 5;
        }
    }

    public void die() {

    }

    public void attack() {

    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public int getPower() {
        return power;
    }

    public int getSpeedX() {
        return speedX;
    }

    public int getCenterX() {
        return centerX;
    }

    public int getCenterY() {
        return centerY;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }

    public void setSpeedY(int speedY) {
        this.speedY = speedY;
    }

    public void setCenterX(int centerX) {
        this.centerX = centerX;
    }

    public void setCenterY(int centerY) {
        this.centerY = centerY;
    }

    public void setImage(Image tileImage) {
        this.tileImage = tileImage;
    }

    public Image getImage() {
        return tileImage;
    }
}
