/*
 * ClickzFrame.java
 *
 * Created on February 17, 2005, 9:21 AM
 */



import java.awt.*;

/**
 * @Erik Schumacher
 */
public class ClickzFrame
{
    private int Score;
    private int lastScore;
    private long startTime, currentTime;
    private long lastTime;
    private final long finishTime = 60000;
    private boolean GameOver;
    private boolean clearTimeArea = true;
    private boolean clearScoreArea = true;
    private boolean initialized = false;
    
    public ClickzFrame()
    {
        startTime = System.currentTimeMillis();
        lastTime = 0;
        GameOver = false;
        Score = 0;
        lastScore = -1;
    }
    
    public void update(ClickzGame game)
    {
        currentTime = System.currentTimeMillis();
        
        if (currentTime - startTime >= finishTime)
            GameOver = true;
        
        clearTimeArea = !(lastTime == (60 - ((currentTime - startTime) / 1000)));
        lastTime = (60 - ((currentTime - startTime) / 1000));
        
        clearScoreArea = !(lastScore == game.getScore());
        lastScore = Score;
        Score = game.getScore();
    }
    
    public void draw(Graphics page)
    {
        if (!initialized)
            drawInitialize(page, page.getFont());
        
        page.setColor(Color.black);
        page.drawRect(19, 49, 462, 402);
        
        if (clearTimeArea)
            page.clearRect(200, 20, 40, 29);
        if (clearScoreArea)
            page.clearRect(60, 20, 40, 29);
        
        page.drawString("Score: " + Score, 20, 48);
        page.drawString("Time Left: " + (60 - ((currentTime - startTime) / 1000)), 140, 48);
    }
    
    public void drawInitialize(Graphics page, Font oldFont)
    {
        page.setFont(new Font("Times New Roman", Font.PLAIN, 24));
        page.setColor(Color.black);
        
        page.drawString("By Erik Schumacher", 144, 490);
        
        page.setFont(oldFont);
        
        initialized = true;
    }
    
    public boolean isGameOver()
    {
        return GameOver;
    }
}
