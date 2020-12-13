/*
 * Spot.java
 *
 * Created on February 17, 2005, 9:11 AM
 */



import java.util.Random;
import java.awt.*;

/**
 * @Erik Schumacher
 */
public class Spot
{
    private int X, Y;
    private int Size = 20;
    private int Life;
    private boolean Hit;
    
    private Random gen;
    private Rectangle rec;
    
    public Spot(int Width, int Height, int addX, int addY, int life)
    {
        gen = new Random();
        
        Hit = false;
        Life = life;
        
        X = gen.nextInt(Width - Size + 1) + addX;
        Y = gen.nextInt(Height - Size + 1) + addY;
        
        rec = new Rectangle(X, Y, Size, Size);
    }
    
    public void draw(Graphics page)
    {
        page.setColor(Color.red);
        page.fillOval(X, Y, Size, Size);
        Life --;
    }
    
    public void checkHit(int x, int y)
    {
        if (rec.contains(x, y))
            Hit = true;
    }
    
    public boolean isHit()
    {
        return Hit;
    }
    
    public int getLife()
    {
        return Life;
    }
}
