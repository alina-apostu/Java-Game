package PaooGame.Items;

import PaooGame.Graphics.Assets;
import PaooGame.Items.NPC.RedSpider;
import PaooGame.PublicGameData;
import PaooGame.RefLinks;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class FireBall
{
    private float x;
    private float y;
    private float speed = 3f;
    private int direction; // 1 = dreapta, -1 = stanga
    private boolean active = true;
    private BufferedImage image;
    private RefLinks refLinks;
    private ArrayList<Character> enemies;

    public FireBall(RefLinks refLinks, float x, float y, int direction, ArrayList<Character> enemies)
    {
        this.refLinks = refLinks;
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.enemies = enemies;
        if(direction == 1)
            this.image = Assets.fireball;
        else
            this.image = flipImageHorizontally(Assets.fireball);
    }

    public void Update()
    {
        x += speed * direction;

        // dezactivam daca iese din ecran
        /*if(x<0 || x>refLinks.GetWidth())
        {
            active = false;
        }*/

        for(Character enemy : enemies)
        {
            if((enemy instanceof SpiderBlue) || (enemy instanceof RedSpider) || (enemy instanceof ShadowSpider))
                if(getBounds().intersects(enemy.getBounds()))
                {
                    enemy.Die();
                    if(enemy instanceof SpiderBlue || enemy instanceof RedSpider)
                        PublicGameData.addScore(75);
                    if(enemy instanceof ShadowSpider)
                        PublicGameData.addScore(500);
                    active = false;
                }
        }
    }

    public void Draw(Graphics g)
    {
        if(active)
        {
            g.drawImage(image, (int)x, (int)y, 64, 64, null);
        }
    }

    public boolean isActive()
    {
        return active;
    }

    public void setActive(boolean active)
    {
        this.active = active;
    }

    public float getX()
    {
        return x;
    }

    public float getY()
    {
        return y;
    }

    public Rectangle getBounds()
    {
        return new Rectangle((int)x, (int)y, 32,32);
    }

    private BufferedImage flipImageHorizontally(BufferedImage img) {
        int width = img.getWidth();
        int height = img.getHeight();
        BufferedImage flippedImg = new BufferedImage(width, height, img.getType());
        Graphics2D g = flippedImg.createGraphics();
        g.drawImage(img, 0, 0, width, height, width, 0, 0, height, null);
        g.dispose();
        return flippedImg;
    }
}
