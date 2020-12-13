/*
 * Clickz.java
 *
 * Created on February 17, 2005, 9:32 AM
 */



import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * @Erik Schumacher
 */
public class Clickz extends JFrame implements MouseListener, MouseMotionListener
{
    boolean FontUpdated = false;
    boolean statsMode = false;
    
    ClickzGame Game;
    ClickzFrame Frame;
    Rectangle GameRect;
    
    myButton PlayAgain, Exit, ViewStats, ExitStats;
    
    public void newGame()
    {
        Game = new ClickzGame(460, 400, 20, 50);
        Frame = new ClickzFrame();
        GameRect = new Rectangle(20, 50, 400, 460);
        PlayAgain = null;
        Exit = null;
        ViewStats = null;
        ExitStats = null;
        FontUpdated = false;
        statsMode = false;
    }
    
    public Clickz()
    {
        super("Clickz");
        addMouseListener(this);
        addMouseMotionListener(this);
        setSize(500, 500);
        setVisible(true);
        setBackground(Color.lightGray);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(200, 50);
        
        newGame();
        repaint();
    }
    
    public static void main(String[] args)
    {
        Clickz frame = new Clickz();
    }
    
    public void paint(Graphics page)
    {
        if (!FontUpdated)
            page.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        
        if (statsMode)
        {
            if (!Game.isStatsDrawn())
            {
                ExitStats = new myButton("Go Back", 194, 350, 180, 26);
                Game.drawStats(page, page.getFont());
            }
            
            ExitStats.draw(page, page.getFont());
            
            if (ExitStats.isClicked())
            {
                statsMode = false;
                Game.setGameOverDrawn(false);
            }
        }
        else if (Frame.isGameOver())
        {
            if (!Game.isGameOverDrawn())
            {
                Game.drawGameOver(page, page.getFont());
                PlayAgain = new myButton("Play Again", 195, 240, 200, 26);
                Exit = new myButton("Exit", 223, 280, 140, 26);
                ViewStats = new myButton("View Stats", 195, 320, 200, 26);
            }
            
            PlayAgain.draw(page, page.getFont());
            Exit.draw(page, page.getFont());
            ViewStats.draw(page, page.getFont());
            
            if (PlayAgain.isClicked())
                newGame();
            else if (Exit.isClicked())
                System.exit(0);
            else if (ViewStats.isClicked())
            {
                statsMode = true;
                Game.setStatsDrawn(false);
            }
        }
        else
        {
            Frame.update(Game);
            Frame.draw(page);
            Game.draw(page);

            try{ 
                Thread.sleep(10);
            }
            catch(InterruptedException ie){ie.printStackTrace();}
        }
        
        repaint();
    }
    
    public void mouseClicked(java.awt.event.MouseEvent e)
    {
        if (statsMode)
            ExitStats.checkClicked(e.getX(), e.getY());
        else if (Frame.isGameOver())
        {
            PlayAgain.checkClicked(e.getX(), e.getY());
            Exit.checkClicked(e.getX(), e.getY());
            ViewStats.checkClicked(e.getX(), e.getY());
        }
        else if (GameRect.contains(e.getX(), e.getY()))
            Game.checkHit(e.getX(), e.getY());
    }
    
    public void mouseEntered(java.awt.event.MouseEvent e){}
    
    public void mouseExited(java.awt.event.MouseEvent e){}
    
    public void mousePressed(java.awt.event.MouseEvent e){}
    
    public void mouseReleased(java.awt.event.MouseEvent e){}
    
    public void mouseDragged(java.awt.event.MouseEvent e){}
    
    public void mouseMoved(java.awt.event.MouseEvent e)
    {
        if (Frame.isGameOver())
        {
            if (PlayAgain != null)
                PlayAgain.checkOver(e.getX(), e.getY());
            if (Exit != null)
                Exit.checkOver(e.getX(), e.getY());
            if (ViewStats != null)
                ViewStats.checkOver(e.getX(), e.getY());
            
            if (ExitStats != null)
                ExitStats.checkOver(e.getX(), e.getY());
        }
    }
    
}
