import java.awt.Rectangle;
import java.awt.Image;
public class Tile {

    public int centerX, centerY, speedX, speedY, type;
    public Rectangle r;
    public Image tileImage;
    public Player player = StartingClass.getplayer();
    // Behavioral Methods

    public Tile(int centerX, int centerY, int typeInt)
    {
        type = typeInt;
        setCenterX(centerX);
        setCenterY(centerY);
        r = new Rectangle();
        if (type == 1) {
            tileImage = StartingClass.tile;
        } else if (type == 2) {
            tileImage = StartingClass.floor;
        }
        else{
            type = 0;
        }
    }

    public void update() {                
        
        //speedX = player.getSpeedX();
        //speedY = player.getSpeedY();
        
        centerX += speedX;
        centerY += speedY;
        
        r.setBounds(centerX-25, centerY-25, 50, 50);
        if(r.intersects(Player.CollisionZone) && type == 1) {
            checkVerticalCollision(Player.Top, Player.Bottom);
            checkSideCollision(Player.Left, Player.Right);
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
