import java.util.ArrayList;
public class Grid {

    public int centerX, centerY, speedX, speedY;
    public ArrayList<Grid> grid = new ArrayList<Grid>();
    public Player player = StartingClass.getplayer();
 
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
