import java.awt.Rectangle;
public class Goblin extends Enemy{

    public Goblin(int maxHealth, int currentHealth, int power, int speedX ,int speedY, int centerX, int centerY, int type){
        setCenterX(centerX);
        setCenterY(centerY);
        setMaxHealth(maxHealth);
        setCurrentHealth(currentHealth);
        setPower(power);
        setSpeedX(speedX);
        setSpeedY(speedY);
        r = new Rectangle();

        if (type == 1)
        {
            tileImage = StartingClass.goblin;
        }
        else { 
            type = 0;
        }
    }
    
    public void update() {
        
        r.setBounds(centerX, centerY, 40, 40);
        if(r.intersects(Player.CollisionZone) ) {
                    follow();
            checkVerticalCollision(Player.Top, Player.Bottom);
            checkSideCollision(Player.Left, Player.Right);
        }
    }

}