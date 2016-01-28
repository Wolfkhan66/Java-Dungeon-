import java.util.ArrayList;
import java.awt.Rectangle;
public class Grid {

    public int centerX, centerY, speedX, speedY;
    public ArrayList<Grid> grid = new ArrayList<Grid>();
    public Player player = StartingClass.getplayer();
     private Rectangle r;
    // Behavioral Methods
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
        
         r = new Rectangle();

        r.setBounds(centerX, centerY, 50, 50);

        if (r.intersects(Player.CollisionZone)) {
            checkVerticalCollision(Player.Top, Player.Bottom);
            checkSideCollision(Player.Left, Player.Right);
        }
    }
    public void checkVerticalCollision(Rectangle rtop, Rectangle rbot){
        
        if (rtop.intersects(r)) {
            player.setSpeedY(0);
            player.setCenterY(centerY + 102);
        }
        if (rbot.intersects(r)) {
            player.setSpeedY(0);
            player.setCenterY(centerY - 63);
        }
    }
    public void checkSideCollision(Rectangle rleft, Rectangle rright) {
        if (rleft.intersects(r)) {
            player.setCenterX(centerX + 102);
            player.setSpeedX(0);

        }
        if (rright.intersects(r)) {
            player.setCenterX(centerX - 62);
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
}
