package PaooGame.Tiles.ForLevel1.Carti;

import PaooGame.Graphics.Assets;
import PaooGame.Tiles.Tile;

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

}
