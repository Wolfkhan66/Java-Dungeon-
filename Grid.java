import java.util.ArrayList;
public class Grid {

    private int centerX, centerY;
    public ArrayList<Grid> grid = new ArrayList<Grid>();
 
    // Behavioral Methods
    public void update() {
 

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
