import java.awt.Rectangle;
public class Tile extends Map
{
    public Tile(int centerX, int centerY, int typeInt)
    {
        type = typeInt;
        setCenterX(centerX);
        setCenterY(centerY);
        r = new Rectangle();
        if (type == 1) {
            tileImage = StartingClass.tile;
        }
        else     if (type == 6) {
            tileImage = StartingClass.minimap;
        }else{
            type = 0;
        }
    }

    public void update() {                

        r.setBounds(centerX, centerY, 40, 40);
        if(r.intersects(Player.CollisionZone) && type == 1) {
            checkVerticalCollision(Player.Top, Player.Bottom);
            checkSideCollision(Player.Left, Player.Right);
        }
    }
}
