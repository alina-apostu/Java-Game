package PaooGame.Tiles.ForLevel3.TipuriLemn;

import PaooGame.Graphics.Assets;
import PaooGame.Tiles.Tile;

public class LemnObstacol extends Tile
{

        public LemnObstacol(int id)
        {
            /// Apel al constructorului clasei de baza
            super(Assets.lemn, id);
        }

    public boolean IsSolid()
    {
        return true;
    }


}
