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
    private boolean consumed = false; // se face true cand pisica mananca soricelul si ii ia puterea

    // sclipici
    private boolean showSparkle = false;
    private long sparkleStartTime;
    private static final long sparkleDuration = 350; // ms
    private String sparkleColor;

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
        sparkleColor = currentColor;
        mouseIndex = 0; // incepem de la primul cadru
        lastFrameTime = System.currentTimeMillis();

        normalBounds.x = 16;
        normalBounds.y = 16;
        normalBounds.width = 32;
        normalBounds.height = 32;
    }
    public void Update()
    {
        if(consumed)
        {
            if(showSparkle == true && System.currentTimeMillis() - sparkleStartTime > sparkleDuration)
                showSparkle = false; // gata
            return; // nu mai dam update la soarece
        }

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
        if(consumed)
        {
            if(showSparkle)
                if(sparkleColor.equals("purple"))
                    g.drawImage(Assets.purple_sparkle, (int)(x+10), (int)y, width, height, null);
                else if(sparkleColor.equals("green"))
                    g.drawImage(Assets.green_sparkle, (int)(x+10), (int)y, width, height, null);
                else if(sparkleColor.equals("blue"))
                    g.drawImage(Assets.blue_sparkle, (int)(x+10), (int)y, width, height, null);
            return; // nu mai desenam soarecele
        }
        g.setColor(Color.GREEN);
        Rectangle bounds = getBounds();
        g.drawRect(bounds.x, bounds.y, bounds.width, bounds.height);
        g.drawImage(currentMouse[mouseIndex], (int)x, (int)y, width, height, null );
    }

    public boolean isConsumed()
    {
        return consumed;
    }

    public String getCurrentColor()
    {
        return currentColor;
    }

    public void setConsumed(boolean consumed)
    {
        this.consumed = consumed;
    }

    @Override
    public Rectangle getBounds()
    {
        return new Rectangle((int) (x + 20), (int) (y + 45), width-34, height-45);
    }

    public void showSparkle()
    {
        showSparkle = true;
        sparkleStartTime = System.currentTimeMillis();
    }

    @Override
    public void Die()
    {

    }
}
