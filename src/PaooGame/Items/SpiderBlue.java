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

    public SpiderBlue(RefLinks refLink, float x, float y) {
        super(refLink, x, y, Character.DEFAULT_CREATURE_WIDTH, Character.DEFAULT_CREATURE_HEIGHT);

        moveRight = Assets.spiderBlueRight; // Asigură-te că ai imagini în Assets.spiderRight
        image = moveRight[0];
        characterIndex = 0;
        lastTime = System.currentTimeMillis();

        leftLimit = x - 50;   // cât se mișcă în stânga
        rightLimit = x + 50;  // cât se mișcă în dreapta
        speed = 1.0f;

        normalBounds.x = 16;
        normalBounds.y = 16;
        normalBounds.width = 32;
        normalBounds.height = 32;
    }

    @Override
    public void Update() {
        // Mișcare automată
        if (movingRight) {
            x += speed;
            if (x > rightLimit) movingRight = false;
        } else {
            x -= speed;
            if (x < leftLimit) movingRight = true;
        }

        Move();

        //  animația
        long currentTime = System.currentTimeMillis();
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
    public void Draw(Graphics g) {
        g.drawImage(image, (int)x, (int)y, width, height, null);
    }
}
