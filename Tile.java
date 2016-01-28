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
        } else if (type == 2) {
            tileImage = StartingClass.floor;
        }
        else{
            type = 0;
        }
    }
}
