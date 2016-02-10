import java.awt.Rectangle;
import java.awt.Image;
import java.util.ArrayList;
public class Map {

    public int centerX, centerY, speedX, speedY, type ;
    public Rectangle r;
    public Image tileImage;
    public Player player = StartingClass.getplayer();
    public Minimapplayer minimapplayer = StartingClass.getminimapplayer();
    public Camera camera = StartingClass.getcamera();
    // Behavioral Methods

    public static int x = 0;
    public static int y = 0;

    public static int[][]world = {{ 4, 10,  7,  3,  6,  10,  7,  6, 12,  2},
            { 3,  4, 13,  5, 15, 12, 13, 11, 14,  2},
            {11,  7,  5,  7,  5, 15, 15,  8,  3,  3},
            { 5, 14,  2,  5,  7, 11, 14, 10,  8,  9},
            { 3,  4, 12, 12, 13, 11,  7,  4, 10, 13},
            { 1,  6, 14, 14,  8,  9,  5,  2,  3,  9},
            { 6, 15,  7,  6, 10, 15, 10,  2,  9,  1},
            {11, 14, 15, 15, 12, 15,  7,  3,  5,  7},
            { 9,  3, 11, 15, 15, 13,  5, 15,  7,  1},
            { 1,  1,  1,  1,  1,  5,  2,  5, 14,  2}} ;

    public static int area = world[x][y]; 

    public void update() {   

    }

    public void checkDoorVerticalCollision(Rectangle rtop, Rectangle rbot){
       /* if (rtop.intersects(r)) {
            player.setCenterY(400);
            y --; 
            StartingClass.area = world[x][y];;
            StartingClass.roomchange = true;
            minimapplayer.setCenterY(minimapplayer.getCenterY() - 30);
        }
        if (rbot.intersects(r)) {
            player.setCenterY(80);
            y ++;
            StartingClass.area = world[x][y];;
            StartingClass.roomchange = true;
            minimapplayer.setCenterY(minimapplayer.getCenterY() + 30);
        }*/
    }

    public void checkDoorSideCollision(Rectangle rleft, Rectangle rright) {
        /* if (rleft.intersects(r)) {
            player.setCenterX(720);
            x --;
            StartingClass.area = world[x][y];;
            StartingClass.roomchange = true;
            minimapplayer.setCenterX(minimapplayer.getCenterX() - 30);
        }
        if (rright.intersects(r)) {
            player.setCenterX(80);          
            x ++;
            StartingClass.area = world[x][y];;
            StartingClass.roomchange = true;
            minimapplayer.setCenterX(minimapplayer.getCenterX() + 30);
        } */
    }

    public void checkVerticalCollision(Rectangle rtop, Rectangle rbot){
        if (rtop.intersects(r)) {
            player.stopUp();
            player.setSpeedY(0);
            player.setCenterY(player.getCenterY() + 5);
            camera.stopUp();
            camera.setSpeedY(0);
            camera.setCenterY(camera.getCenterY() + 5);
        }
        if (rbot.intersects(r)) {
            player.stopDown();
            player.setSpeedY(0);
            player.setCenterY(player.getCenterY() - 5);
            camera.stopDown();
            camera.setSpeedY(0);
            camera.setCenterY(camera.getCenterY() - 5);
        }
    }

    public void checkSideCollision(Rectangle rleft, Rectangle rright) {
        if (rleft.intersects(r)) {
            player.stopLeft();
            player.setSpeedX(0);
            player.setCenterX(player.getCenterX() + 5);
            camera.stopLeft();
            camera.setSpeedX(0);
            camera.setCenterX(camera.getCenterX() + 5);
        }
        if (rright.intersects(r)) {
            player.stopRight();
            player.setSpeedX(0);
            player.setCenterX(player.getCenterX() - 5);
            camera.stopRight();
            camera.setSpeedX(0);
            camera.setCenterX(camera.getCenterX() - 5);
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
