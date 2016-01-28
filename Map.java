import java.awt.Rectangle;
import java.awt.Image;
import java.util.ArrayList;
public class Map {

    public int centerX, centerY, speedX, speedY, type;
    public Rectangle r;
    public Image tileImage;
    public Player player = StartingClass.getplayer();
    // Behavioral Methods
    public ArrayList<Map> map = new ArrayList<Map>();

    public void update() {                

    }

    public void checkDoorVerticalCollision(Rectangle rtop, Rectangle rbot){
        if (rtop.intersects(r)) {
            player.setCenterY(400);
        }
        if (rbot.intersects(r)) {
            player.setCenterY(80);
        }
    }

    public void checkDoorSideCollision(Rectangle rleft, Rectangle rright) {
          if (rleft.intersects(r)) {
            player.setCenterX(720);
        }
        if (rright.intersects(r)) {
            player.setCenterX(80);
        }
    }

    public void checkVerticalCollision(Rectangle rtop, Rectangle rbot){
        if (rtop.intersects(r)) {
            player.stopUp();
            player.setSpeedY(0);
            player.setCenterY(player.getCenterY() + 5);
        }
        if (rbot.intersects(r)) {
            player.stopDown();
            player.setSpeedY(0);
            player.setCenterY(player.getCenterY() - 5);
        }

    }
        public void checkSideCollision(Rectangle rleft, Rectangle rright) {
        if (rleft.intersects(r)) {
            player.stopLeft();
            player.setSpeedX(0);
            player.setCenterX(player.getCenterX() + 5);
        }
        if (rright.intersects(r)) {
            player.stopRight();
            player.setSpeedX(0);
            player.setCenterX(player.getCenterX() - 5);
        }

    }

    public int getCenterX() {
        return centerX;
    }

    public int getCenterY() {
        return centerY;
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
