import java.awt.Rectangle;
import java.awt.Image;
public class Door extends Map
{

    /**
     * Constructor for objects of class Door
     */
    public Door(int centerX, int centerY, int typeInt) 
    {
        type = typeInt;
        setCenterX(centerX);
        setCenterY(centerY);
        r = new Rectangle();
        if (type == 1) {
            tileImage = StartingClass.door1; // top
        } else if (type == 2) {
            tileImage = StartingClass.door1; // botom
        }else if (type == 3) {
            tileImage = StartingClass.door2; // left
        }else if (type == 4) {
            tileImage = StartingClass.door2; // right
        }
        else{
            type = 0;
        }
    }

}

