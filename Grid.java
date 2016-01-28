import java.util.ArrayList;
import java.awt.Rectangle;
import java.awt.Image;
public class Grid {

    public int centerX, centerY, speedX, speedY;
    public ArrayList<Grid> grid = new ArrayList<Grid>();
    public Player player = StartingClass.getplayer();
    public Rectangle r;
    public Image tileImage;
    // Behavioral Methods
    public void update() {                
        r = new Rectangle();
        r.setBounds(centerX, centerY, 5, 5);

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

        if (r.intersects(Player.CollisionZone)) {
            checkVerticalCollision(Player.Top, Player.Bottom);
            checkSideCollision(Player.Left, Player.Right);
        }
        
        centerX += speedX;
        centerY += speedY;

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
