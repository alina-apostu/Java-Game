package PaooGame.Tiles.ForLevel1.Carti;

import PaooGame.Graphics.Assets;
import PaooGame.Tiles.Tile;

import java.awt.*;

public class Carte1 extends Tile
{

        public Carte1(int id)
        {
            /// Apel al constructorului clasei de baza
            super(Assets.carte1, id);
        }

    @Override
    public boolean IsSolid()
    {
        return true;
    }

    /*@Override
    public void Draw(Graphics g, int x, int y) {
        g.drawImage(img, x, y, 48, 48, null);
    }*/


}
