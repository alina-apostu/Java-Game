package PaooGame.Tiles.ForLevel3.Geam;

import PaooGame.Graphics.Assets;
import PaooGame.Tiles.Tile;

import java.awt.*;

public class Geam6 extends Tile
{

        public Geam6(int id)
        {
            /// Apel al constructorului clasei de baza
            super(Assets.geam6, id);
        }

    @Override
    public void Draw(Graphics g, int x, int y) {
        g.drawImage(img, x, y, 32, 32, null);
    }

}
