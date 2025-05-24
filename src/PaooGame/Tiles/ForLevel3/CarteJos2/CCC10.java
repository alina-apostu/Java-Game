package PaooGame.Tiles.ForLevel3.CarteJos2;

import PaooGame.Graphics.Assets;
import PaooGame.Tiles.Tile;

public class CCC10 extends Tile
{

        public CCC10(int id)
        {
            /// Apel al constructorului clasei de baza
            super(Assets.ccc10, id);
        }
    public boolean IsSolid()
    {
        return true;
    }

}
