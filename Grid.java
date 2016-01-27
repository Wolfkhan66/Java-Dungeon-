import java.util.ArrayList;
public class Grid {

    private int centerX, centerY, speedX, speedY;
    public ArrayList<Grid> grid = new ArrayList<Grid>();
 
    // Behavioral Methods
    public void update() {
         centerX += speedX;
        centerY += speedY;

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
