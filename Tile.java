import java.awt.Rectangle;
public class Tile extends Grid
{
    public Tile(int centerX, int centerY)
    {
        setCenterX(centerX);
        setCenterY(centerY);
        tileImage = StartingClass.tile;
    }
}
