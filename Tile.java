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

                r.setBounds(centerX, centerY, 50, 50);
        if(r.intersects(Player.CollisionZone) && type == 1) {
            checkVerticalCollision(Player.Top, Player.Bottom);
            checkSideCollision(Player.Left, Player.Right);
        }
        
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

        centerX += speedX;
        centerY += speedY;
    }

    public void checkVerticalCollision(Rectangle rtop, Rectangle rbot){
        if (rtop.intersects(r)) {
            centerX -= player.getSpeedX();
        }
        if (rbot.intersects(r)) {
            centerX -= player.getSpeedX();
        }
    }

    public void checkSideCollision(Rectangle rleft, Rectangle rright) {
        if (rleft.intersects(r)) {
            centerY -= player.getSpeedY();
        }
        if (rright.intersects(r)) {
            centerY -= player.getSpeedY();
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
