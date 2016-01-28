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

        centerX += speedX;
        centerY += speedY;

        if (player.getCenterX() < 0) {
            centerX += player.getSpeedX(); //This changes centerX by adding speedX.
        }
        else if (player.getCenterX() > 0) {
            centerX += player.getSpeedX(); //This changes centerX by adding speedX.
        }

        if (player.getCenterY() < 0) {
            centerY += player.getSpeedY(); //This changes centerY by adding speedY.
        }
        else if (player.getCenterY() > 0) {
            centerY += player.getSpeedY(); //This changes centerY by adding speedY.
        }

        r.setBounds(centerX, centerY, 50, 50);
        if(r.intersects(Player.CollisionZone)) {
            checkVerticalCollision(Player.Top, Player.Bottom);
            checkSideCollision(Player.Left, Player.Right);
        }
    }

    public void checkVerticalCollision(Rectangle rtop, Rectangle rbot){
        if (rtop.intersects(r)) {
            player.setSpeedY(0);
        }
        if (rbot.intersects(r)) {
            player.setSpeedY(0);
        }
    }

    public void checkSideCollision(Rectangle rleft, Rectangle rright) {
            if (rleft.intersects(r)) {
                player.setSpeedX(0);
            }
            if (rright.intersects(r)) {
                player.setSpeedX(0);
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
