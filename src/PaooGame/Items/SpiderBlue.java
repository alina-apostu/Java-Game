package PaooGame.Items;

import PaooGame.Graphics.Assets;
import PaooGame.RefLinks;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SpiderBlue extends Character {
    private BufferedImage[] moveRight;
    private BufferedImage image;
    private int characterIndex;
    private long lastTime;
    private boolean movingRight = true;
    private float leftLimit, rightLimit;

    // moarte
    private BufferedImage[] death;
    private int deathIndex = 0;
    private long lastDeathFrameTime = 0;
    private final long deathFrameInterval = 150; // viteza cu care se schimba cadrele
    private boolean dying = false;
    private boolean dead = false;

    public SpiderBlue(RefLinks refLink, float x, float y)
    {
        super(refLink, x, y, Character.DEFAULT_CREATURE_WIDTH, Character.DEFAULT_CREATURE_HEIGHT);

        moveRight = Assets.blueSpiderRight;
        death = Assets.blueSpiderDeath;

        image = moveRight[0];
        characterIndex = 0;
        lastTime = System.currentTimeMillis();

        leftLimit = x - 50;
        rightLimit = x + 50;
        speed = 1.0f;

        normalBounds.x = 16;
        normalBounds.y = 16;
        normalBounds.width = 32;
        normalBounds.height = 32;
    }

    @Override
    public void Update()
    {
        if(dead == true)
            return;

        long currentTime = System.currentTimeMillis();

        if(dying == true)
        {
            if(currentTime - lastDeathFrameTime >= deathFrameInterval)
            {
                lastDeathFrameTime = currentTime;
                if(deathIndex < death.length-1)
                {
                    deathIndex++;
                    image = death[deathIndex];
                }
                else
                {
                    dead = true;
                    dying = false;
                }
            }

            return; // nu mai facem update daca moare;
        }

        // Mișcare automată
        if (movingRight)
        {
            x += speed;
            if (x > rightLimit) movingRight = false;
        }
        else
        {
            x -= speed;
            if (x < leftLimit) movingRight = true;
        }

        Move();

        //  animația
        if (currentTime - lastTime > 200) // 200 ms între cadre
        {
            characterIndex++;
            lastTime = currentTime;
        }


        if (characterIndex >= moveRight.length)
        {
            characterIndex = 0;
        }

        if (movingRight)
        {
            image = moveRight[characterIndex];
        }
        else
        {
            image = flipImageHorizontally(moveRight[characterIndex]);
        }
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

    @Override
    public void Draw(Graphics g)
    {
        /*g.setColor(Color.GREEN);
        Rectangle bounds = getBounds();
        g.drawRect(bounds.x, bounds.y, bounds.width, bounds.height);*/

        g.drawImage(image, (int) x, (int) y, width, height, null);

        /*g.setColor(Color.RED);
        Rectangle r = getBounds();
        g.drawRect(r.x, r.y, r.width, r.height);*/
    }

    @Override
    public Rectangle getBounds()
    {
        return new Rectangle((int) (x + 25), (int) (y + 45), width - 40, height - 45);
    }

    @Override
    public void Die()
    {
        dying = true;
        dead = false;
        deathIndex = 0;
        image = death[deathIndex];
        lastDeathFrameTime = System.currentTimeMillis();
    }

    public boolean isDead()
    {
        return dead;
    }
}