package PaooGame.Tiles.ForLevel1.Carti;

import PaooGame.Graphics.Assets;
import PaooGame.Tiles.Tile;

public class Carte2 extends Tile
{

        public Carte2(int id)
        {
            /// Apel al constructorului clasei de baza
            super(Assets.carte2, id);
        }

    @Override
    public boolean IsSolid()
    {
        return true;
    }

}
