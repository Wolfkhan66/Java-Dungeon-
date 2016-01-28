import java.awt.Rectangle;
public class Tile extends Grid
{
    // instance variables - replace the example below with your own

    private Rectangle r;

    /**
     * Constructor for objects of class Tile
     */
    public Tile(int centerX, int centerY)
    {
        // initialise instance variables
        setCenterX(centerX);
        setCenterY(centerY);

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
}
