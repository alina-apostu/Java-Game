package PaooGame.Items;

import PaooGame.Graphics.Assets;
import PaooGame.RefLinks;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ShadowSpider extends Character {
    private BufferedImage image;
    private BufferedImage[] walk;
    private BufferedImage[] attack;
    private BufferedImage[] web;

    // indici pentru a tine evidenta cadrului curent din fiecare animatie
    private int walkIndex = 0;
    private int attackIndex = 0;
    private int webIndex = 0;

    private long lastFrameTime = 0; // timpul la care a fost redat ultimul cadru
    private final long frameInterval = 200; // intervalul la care se schimba cadrele

    private boolean movingForward = true; // true = inainte, false = inapoi
    private boolean isAttacking = false; // daca paianjenul este in animatie atac
    private boolean isWebVisible = false; // daca panza trebuie desenata pe ecran
    private boolean isReturning = false;

    private float startX = 0;
    private final int moveDistance = 60;

    // pentru panza
    private int webX = 0;
    private int webY = 0;

    private long lastWebFrameTime = 0;
    private final long webFrameInterval = 350; // animatia panzei se schimba la 200ms

    // moarte
    private BufferedImage[] death;
    private int deathIndex = 0;
    private long lastDeathFrameTime = 0;
    private final long deathFrameInterval = 150; // viteza cu care se schimba cadrele
    private boolean dying = false;
    private boolean dead = false;

    public ShadowSpider(RefLinks refLink, float x, float y)
    {
        super(refLink, x, y, 120, 120);

        walk = Assets.shadowSpiderWalk;
        attack = Assets.shadowSpiderAttack;
        web = Assets.web;
        death = Assets.shadowSpiderDeath;

        image = walk[0];
        startX = x; // pozitia initiala

        normalBounds.x = 32;
        normalBounds.y =32;
        normalBounds.width = 64;
        normalBounds.height = 64;
    }

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
        if (isWebVisible == true)
        {
            if (currentTime - lastWebFrameTime >= webFrameInterval) {
                lastWebFrameTime = currentTime;
                if (webIndex < web.length - 1)
                    webIndex++;
                else
                {
                    webIndex = 0;
                    isWebVisible = false;
                }
            }
        }

        if (isAttacking == true)
        {
            if (currentTime - lastFrameTime >= frameInterval)
            {
                lastFrameTime = currentTime;
                image = attack[attackIndex];

                if (movingForward == false)
                    image = flipImageHorizontally(image);

                if (attackIndex == 4 && movingForward == true && isWebVisible == false) {
                    isWebVisible = true;
                    webIndex = 0;
                    lastWebFrameTime = currentTime;
                    webX = (int) x - 30;
                    webY = (int) y + 5;
                }

                attackIndex++;
                if (attackIndex >= attack.length)
                {
                    isAttacking = false;
                    attackIndex = 0;
                    isReturning = true;
                    movingForward = false;
                }
            }
            return;
        }

        if (currentTime - lastWebFrameTime >= frameInterval)
        {
            lastFrameTime = currentTime;
            image = walk[walkIndex];
            walkIndex++;
            if (walkIndex >= walk.length)
                walkIndex = 0;
            if (movingForward == false)
                image = flipImageHorizontally(image);
        }

        if (movingForward == true && isReturning == false)
        {
            x -= 1;
            if (x <= startX - moveDistance)
            {
                x = startX - moveDistance;
                if (isHeroNearby())
                {
                    isAttacking = true;
                }
                else
                {
                    isReturning = true;
                    movingForward = false;
                }
            }
        }
        else if (isReturning == true)
        {
            x += 1;
            if (x >= startX)
            {
                x = startX;
                isReturning = false;
                movingForward = true;
            }
        }
    }

    private BufferedImage flipImageHorizontally(BufferedImage img)
    {
        int width = img.getWidth();
        int height = img.getHeight();
        BufferedImage flippedImg = new BufferedImage(width, height, img.getType());
        Graphics2D g = flippedImg.createGraphics();
        g.drawImage(img, 0, 0, width, height, width, 0, 0, height, null);
        g.dispose();
        return flippedImg;
    }

    public void Draw(Graphics g)
    {
        /*g.setColor(Color.GREEN);
        Rectangle bounds = getBounds();
        g.drawRect(bounds.x, bounds.y, bounds.width, bounds.height);*/

        // deseneaza paianjenul
        g.drawImage(image, (int) x, (int) y, width, height, null);
        // deseneaza panza
        if (isWebVisible == true && webIndex < web.length) {
            int[] webOffsetsX = {-5, -10, -15, -20, -25, -30};
            int offsetX = webOffsetsX[webIndex];

            g.drawImage(web[webIndex], webX + offsetX, webY, 60, 60, null);
        }
    }

    private boolean isHeroNearby()
    {
        float heroX = refLink.GetHero().GetX();
        float heroY = refLink.GetHero().GetY();

        float dx = heroX - x;
        float dy = heroY - y;
        float dist = (float) Math.sqrt(dx * dx + dy * dy);

        return dist < 120; // poți ajusta pragul de distanță
    }

    public Rectangle getWebBounds()
    {
        if(isWebVisible == true && webIndex < web.length)
        {
            int[] webOffsetsX = {-5, -10, -15, -20, -25, -30};
            int offsetX = webOffsetsX[webIndex];
            return new Rectangle(webX + offsetX + 15, webY + 25, width - 30, height - 30);
        }
        return null;
    }

    @Override
    public Rectangle getBounds()
    {
        return new Rectangle((int) (x + 34), (int) (y + 45), width - 60, height - 100);
    }

    public boolean isWebFullyOpened()
    {
        if(webIndex == 5) return true;
        return false;
    }

    @Override
    public void Die()
    {
        dying = true;
        dead = false;
        deathIndex = 0;
        image = death[deathIndex];
        lastDeathFrameTime = System.currentTimeMillis();
        isAttacking = false;
        isWebVisible = false;
    }

    public boolean isDead()
    {
        return dead;
    }
}