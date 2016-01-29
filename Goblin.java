public class Goblin extends Enemy{

    public Goblin(int maxHealth, int currentHealth, int power, int speedX ,int speedY, int centerX, int centerY, int type){
        setCenterX(centerX);
        setCenterY(centerY);
        setMaxHealth(maxHealth);
        setCurrentHealth(currentHealth);
        setPower(power);
        setSpeedX(speedX);
        setSpeedY(speedY);

        if (type == 1)
        {
            tileImage = StartingClass.goblin;
        }
        else { 
            type = 0;
        }
    }

}