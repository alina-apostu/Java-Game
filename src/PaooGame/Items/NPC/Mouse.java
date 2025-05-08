package PaooGame.Items.NPC;

import PaooGame.Graphics.Assets;
import PaooGame.Items.Character;
import PaooGame.RefLinks;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Mouse extends PaooGame.Items.Character
{
    private BufferedImage[] currentMouse;
    private int mouseIndex; //  pentru cadre
    private long lastFrameTime;
    private final long frameInterval = 200; // intervalul la care se schimba cadrele
    private String currentColor;

    public Mouse(RefLinks refLink, float x, float y, String mouseColor)
    {
        super(refLink, x,y, 50, 50);

        if(mouseColor.equals("green"))
        {
            currentMouse = Assets.greenMouse;
            currentColor = "green";
        }
        else if(mouseColor.equals("blue"))
        {
            currentMouse = Assets.blueMouse;
            currentColor = "blue";
        }
        else if(mouseColor.equals("purple"))
        {
            currentMouse = Assets.purpleMouse;
            currentColor = "purple";
        }
        else
        {
            throw new IllegalArgumentException("Invalid mouse color: " + mouseColor);
        }

        mouseIndex = 0; // incepem de la primul cadru
        lastFrameTime = System.currentTimeMillis();

        normalBounds.x = 16;
        normalBounds.y = 16;
        normalBounds.width = 32;
        normalBounds.height = 32;
    }
    public void Update()
    {
        long currentTime = System.currentTimeMillis();

        // schimbăm cadrul animației dacă a trecut suficient timp
        if(currentTime - lastFrameTime > frameInterval)
        {
            mouseIndex++; // schimbam cadrul
            lastFrameTime = currentTime;

            if(mouseIndex >= currentMouse.length)
                mouseIndex = 0;
        }
    }
    public void Draw(Graphics g)
    {
        g.drawImage(currentMouse[mouseIndex], (int)x, (int)y, width, height, null );
    }
}
