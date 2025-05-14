package PaooGame.Tiles.ForLevel3.Panza;

import PaooGame.Graphics.Assets;
import PaooGame.Tiles.Tile;

import java.awt.*;

public class Panza3 extends Tile
{

        public Panza3(int id)
        {
            /// Apel al constructorului clasei de baza
            super(Assets.panza3, id);
        }

    @Override
    public void Draw(Graphics g, int x, int y) {
        g.drawImage(img, x, y, 58, 58, null);
    }


}
