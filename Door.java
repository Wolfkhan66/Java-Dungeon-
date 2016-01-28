import java.awt.Rectangle;
import java.awt.Image;
public class Door extends Map
{
    public Door(int centerX, int centerY, int typeInt) 
    {
        type = typeInt;
        setCenterX(centerX);
        setCenterY(centerY);
        r = new Rectangle();
        if (type == 2) {
            tileImage = StartingClass.door1; // left
        } else if (type == 3) {
            tileImage = StartingClass.door1; // right
        }else if (type == 4) {
            tileImage = StartingClass.door2; // top
        }else if (type == 5) {
            tileImage = StartingClass.door2; // bottom
        }
        else{
            type = 0;
        }
    }

    public void update() {     

        if ( type == 4 )
        {
            r.setBounds(centerX, centerY, 160, 20);
        }
        else if( type == 5)
        {
            r.setBounds(centerX, centerY +20, 160, 20);
        }
                else if( type == 2)
        {
            r.setBounds(centerX, centerY, 20, 160);
        }
                else if( type == 3)
        {
            r.setBounds(centerX +20, centerY, 10, 160);
        }
        if(r.intersects(Player.CollisionZone) && type != 1) {
            checkDoorVerticalCollision(Player.Top, Player.Bottom);
            checkDoorSideCollision(Player.Left, Player.Right);
        }
    }
}

