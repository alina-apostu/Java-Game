package PaooGame.Tiles.ForLevel3.Geam;

import PaooGame.Graphics.Assets;
import PaooGame.Tiles.Tile;

import java.awt.*;

public class Geam2 extends Tile
{

        public Geam2(int id)
        {
            /// Apel al constructorului clasei de baza
            super(Assets.geam2, id);
        }

    @Override
    public void Draw(Graphics g, int x, int y) {
        g.drawImage(img, x, y, 32, 32, null);
    }
    public boolean IsSolid()
    {
        return true;
    }

}
