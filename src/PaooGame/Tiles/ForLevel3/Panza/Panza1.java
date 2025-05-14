package PaooGame.Tiles.ForLevel3.Panza;

import PaooGame.Graphics.Assets;
import PaooGame.Tiles.Tile;

import java.awt.*;

public class Panza1 extends Tile
{

        public Panza1(int id)
        {
            /// Apel al constructorului clasei de baza
            super(Assets.panza1, id);
        }

    @Override
    public void Draw(Graphics g, int x, int y) {
        g.drawImage(img, x, y, 64, 64, null);
    }

}
