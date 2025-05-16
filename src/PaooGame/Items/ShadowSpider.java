package PaooGame.Items;

import PaooGame.Graphics.Assets;
import PaooGame.RefLinks;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ShadowSpider extends Character
{
    private BufferedImage image;

    public ShadowSpider(RefLinks refLinks, float x, float y)
    {
        super(refLinks, x, y, 64, 64);
        this.image = Assets.shadowSpider;
    }
    public void Update()
    {
        // nicio miscare momentan
    }

    public void Draw(Graphics g)
    {
        g.drawImage(image, (int)x, (int)y, width, height, null);
    }
}
