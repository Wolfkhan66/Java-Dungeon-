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
            tileImage = StartingClass.bottomleftcorner;
        }
        else     if (type == 2) {
            tileImage = StartingClass.bottomrightcorner;
        }
                else     if (type == 3) {
            tileImage = StartingClass.bottomwall;
        }
                else     if (type == 4) {
            tileImage = StartingClass.leftwall;
        }
                else     if (type == 5) {
            tileImage = StartingClass.rightwall;
        }
                else     if (type == 6) {
            tileImage = StartingClass.topleftcorner;
        }
                else     if (type == 7) {
            tileImage = StartingClass.toprightcorner;
        }
                else     if (type == 8) {
            tileImage = StartingClass.topwall;
        }
                else     if (type == 9) {
            tileImage = StartingClass.minimap;
        }else{
            type = 0;
        }
    }

    public void update() {                
        r.setBounds(centerX, centerY, 40, 40);
        if(r.intersects(Player.CollisionZone) && (type == 3 || type == 4 || type == 5 || type == 8 )) {
            checkVerticalCollision(Player.Top, Player.Bottom);
            checkSideCollision(Player.Left, Player.Right);
        }
    }
}
