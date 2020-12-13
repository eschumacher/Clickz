/*
 * ClickzGame.java
 *
 * Created on February 17, 2005, 9:21 AM
 */



import java.awt.*;

/**
 * @Erik Schumacher
 */
public class ClickzGame
{
    private int Score;
    private int Misses;
    private final int Width;
    private final int Height;
    private final int addX;
    private final int addY;
    private int Life;
    private int spotsAppeared;
    
    private boolean GameOverDrawn = false;
    private boolean statsDrawn = false;
    
    private Spot spot;
    
    public ClickzGame(int width, int height, int addx, int addy)
    {
        Width = width;
        Height = height;
        addX = addx;
        addY = addy;
        
        Score = 0;
        Misses = 0;
        Life = 200;
        spotsAppeared = 0;
        spot = new Spot(Width, Height, addX, addY, 0);
    }
    
    public void checkHit(int x, int y)
    {
        spot.checkHit(x, y);
        
        if (!spot.isHit())
            Misses ++;
    }
    
    public void draw(Graphics page)
    {
        page.setColor(Color.white);
        
        if (spot.isHit())
            Score ++;
        
        if (spot.getLife() <= 0 || spot.isHit())
        {
            page.fillRect(addX, addY, Width, Height);
            spot = new Spot(Width, Height, addX, addY, Life);
            spotsAppeared ++;
            if (Life > 25)
                Life --;
        }
        
        spot.draw(page);
    }
    
    public void drawGameOver(Graphics page, Font oldFont)
    {
        //Clear the screen
        page.setColor(Color.white);
        page.fillRect(addX, addY, Width, Height);
        
        //Draw Game Over message
        page.setColor(Color.black);
        page.setFont(new Font("Times New Roman", Font.BOLD, 36));
        page.drawString("Game Over", 152, 200);
        page.setFont(oldFont);
        
        GameOverDrawn = true;
    }
    
    public int getScore()
    {
        return Score;
    }
    
    public boolean isGameOverDrawn()
    {
        return GameOverDrawn;
    }
    
    public void setGameOverDrawn(boolean b)
    {
        GameOverDrawn = b;
    }
    
    public void drawStats(Graphics page, Font oldFont)
    {
        page.setColor(Color.white);
        page.fillRect(addX, addY, Width, Height);

        page.setColor(Color.black);
        page.setFont(new Font("Times New Roman", Font.BOLD, 32));
        page.drawString("Stats", 198, 120);
        
        page.setFont(new Font("Times New Roman", Font.BOLD, 24));
        page.drawString("Hits:", 80, 210);
        page.drawString("Misses:", 80, 240);
        page.drawString("Accuracy:", 80, 270);
        page.drawString("Total Spots:", 80, 300);
        
        page.setFont(new Font("Times New Roman", Font.PLAIN, 24));
        page.drawString("" + Score, 350, 210);
        page.drawString("" + Misses, 350, 240);
        page.drawString("" + ((Score * 100) / (Score + Misses)) + "%", 350, 270);
        page.drawString("" + spotsAppeared, 350, 300);
                
        page.setFont(oldFont);
        
        statsDrawn = true;
    }
    
    public boolean isStatsDrawn()
    {
        return statsDrawn;
    }
    
    public void setStatsDrawn(boolean b)
    {
        statsDrawn = b;
    }
}
