/*
 * myButton.java
 *
 * Created on February 17, 2005, 6:15 PM
 */



import java.awt.*;

/**
 * @author  Erik Schumacher
 */
public class myButton
{
    private String Message;
    private int X, Y;
    private Rectangle Rect;
    private boolean mouseOver = false;
    private boolean changed = false;
    private boolean clicked = false;
    
    public myButton(String message, int x, int y, int width, int height)
    {
        Message = message;
        X = x;
        Y = y + height;
        Rect = new Rectangle(X, y, width, height);
    }
    
    public void draw(Graphics page, Font oldFont)
    {
        if (changed)
        {
            page.setColor(Color.white);
            page.fillRect(X, Y - 18, Rect.width, Rect.height + 18);
        }
        
        if (mouseOver)
            page.setFont(new Font("Times New Roman", Font.BOLD, 26));
        else
            page.setFont(new Font("Times New Roman", Font.BOLD, 22));
        
        page.setColor(Color.black);
        
        page.drawString(Message, X, Y);
        
        page.setFont(oldFont);
    }
    
    public void checkOver(int x, int y)
    {
        if (Rect.contains(x, y))
        {
            changed = !mouseOver;
            mouseOver = true;
        }
        else if (!Rect.contains(x, y) && mouseOver)
        {
            changed = true;
            mouseOver = false;
        }
        else
            changed = false;
    }
    
    public void checkClicked(int x, int y)
    {
        if (Rect.contains(x, y))
            clicked = true;
    }
    
    public boolean isClicked()
    {
        return clicked;
    }
}
